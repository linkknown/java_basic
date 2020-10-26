package com.linkknown.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import com.linkknown.util.FileUtil;
import com.linkknown.util.JAXBUtil;

public class XMLTest {

	/**
	 * ����ֶ�дһ�� xml����ϰ demo.xml �ı�д
	 */
	
/*************************************************** dom4j ********************************************************/	
	
	/**
	 * dom ��ʽ��ȡ xml
	 * 
	 * @throws DocumentException
	 */
	@Test
	public void testReadXML() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader
				.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/dom_users.xml"));
		// asXML ���ַ�����ʽ��ʾ
		System.out.println(document.asXML());
	}

	/**
	 * dom ��ʽ��ȡ xml
	 * 
	 * @throws DocumentException
	 */
	@Test
	public void testReadXML2() throws DocumentException {
		List<User> users = new ArrayList<User>();

		// ��ȡ xml �ļ�, �õ� document �ĵ�����
		SAXReader reader = new SAXReader();
		Document document = reader
				.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/dom_users.xml"));
		// ��ȡ��Ԫ��
		Element rootElement = document.getRootElement();
		// ��ȡ���� user ��Ԫ��
		List elements = rootElement.elements("user");
		Iterator iterator = elements.iterator();
		while (iterator.hasNext()) {
			Element userElement = (Element) iterator.next();
			// ��ȡ��Ԫ��
			Element nameElement = userElement.element("name");
			Element passwordElement = userElement.element("password");
			// ��ȡ����ֵ
			String id = userElement.attributeValue("id");

			User user = new User();
			user.setName(nameElement.getStringValue());
			user.setPassword(passwordElement.getStringValue());
			user.setId(Integer.valueOf(id));
			users.add(user);
		}

		System.out.println(users);
	}

	/**
	 * dom ��ʽд�� xml
	 * @throws IOException
	 */
	@Test
	public void testWriteXML() throws IOException {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setId(i);
			user.setName("tom_" + i);
			user.setPassword("password_" + i);
			users.add(user);
		}
		users.get(0).setPassword("<!-- ����ע�� -->");
		users.get(1).setPassword("<>&'\"");
		users.get(2).setPassword("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<demo>\n����Ƕ�׵�xml\n</demo>");
		users.get(3).setPassword("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<demo>\n����Ƕ�׵�xml\n</demo>");
		
		System.out.println(users);

		// ���� document
		Document document = DocumentHelper.createDocument();
		// �������ڵ�
		Element rootElement = document.addElement("users");
		for (int i=0; i<users.size(); i++) {
			User user = users.get(i);
			Element userElement = rootElement.addElement("user");
			
			userElement.addAttribute("id", user.getId() + "");
			Element nameElement = userElement.addElement("name");
			nameElement.setText(user.getName());
			Element passwordElement = userElement.addElement("password");
			if (i == 3) {
				passwordElement.addCDATA(user.getPassword());
			} else {
				passwordElement.setText(user.getPassword());
			}
		}

		OutputFormat xmlFormat = new OutputFormat();
		// �����ļ�����
		xmlFormat.setEncoding("UTF-8");
		// ���û���
		xmlFormat.setNewlines(true);
		// ��������
		xmlFormat.setIndent(true);
		xmlFormat.setIndent("	");

		XMLWriter writer = new XMLWriter(
				new FileOutputStream(
						"D:\\zhourui\\program\\java\\IDEA\\java_basic\\src\\com\\linkknown\\xml\\dom_write_users.xml"),
				xmlFormat);
		writer.write(document);
		writer.close();
	}

	/*************************************************** sax ********************************************************/
	
	/**
	 * SAX ��ʽ��ȡ��������������ʱ���ļ����ܴ��� 500 M,������ dom ��ʽ��ȡ��ܳԿ�
	 * 
	 * @throws DocumentException
	 */
	@Test
	public void testSAXRead() throws DocumentException {
		SAXReader reader = new SAXReader();
		// ��һ�������� xpath
		reader.addHandler("/users/user", new ElementHandler() {

			@Override
			public void onStart(ElementPath path) {
				System.out.println("��ʼɨ����...");
			}

			@Override
			public void onEnd(ElementPath path) {

				Element userElement = path.getCurrent();
				if ("tom_9998".equals(userElement.element("name").getTextTrim())) {
					System.out.println(userElement.element("password").getTextTrim());
				}
				// ���ڴ�����ȥ
				userElement.detach();
			}
		});

		reader.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/sax_users.xml"));
	}

	/**
	 * SAX ��ʽд xml
	 * @throws TransformerConfigurationException 
	 * @throws SAXException 
	 * @throws IOException 
	 */
	@Test
	public void testSAXWrite () throws TransformerConfigurationException, SAXException, IOException {
		// ����һ��SAXTransformFacotry ���󣬻�ȡ�����������ó�ʼ����
		SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		// ͨ��SAXTransformFacotry ���󴴽�һ��TransformerHandler ���󣬻�ȡ���
		TransformerHandler handler = factory.newTransformerHandler();
		// ͨ��TransformerHandler ���󴴽�һ��transformer ����
		Transformer transformer = handler.getTransformer();
		// ͨ��transformer �������������ʽ, ע�������hanlder ����result֮ǰ���ò���Ч
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");			//ʡ��XML����

		OutputStream outputStream = new FileOutputStream("D:\\zhourui\\program\\java\\IDEA\\java_basic\\src\\com\\linkknown\\xml\\sax_write_users.xml");
		// ����һ��Result ���� ���ҽ�����handler �����������
		handler.setResult(new StreamResult(outputStream));
		
		// ����hanlder �����xml ���б�д
		// ���ĵ�
		handler.startDocument();
		
		handler.startElement("", "", "users", null);
		
		for (int i=0; i<10;i++) {
			String name = "admin";
			String password = "123456";
			String id = i + "";
			
			AttributesImpl attr = new AttributesImpl();
			attr.addAttribute("", "", "id", "", id);
			
			handler.startElement("", "", "user", attr);

			
			handler.startElement("", "", "name", null);
			handler.characters(name.toCharArray(), 0, name.toCharArray().length);
			handler.endElement("", "", "name");
			
			handler.startElement("", "", "password", null);
			handler.characters(password.toCharArray(), 0, password.toCharArray().length);
			handler.endElement("", "", "password");
			
			handler.endElement("", "", "user");
		}
		
		handler.endElement("", "", "users");
		
		handler.endDocument();
		outputStream.close();
	}
	
/*************************************************** xpath ********************************************************/
	
	@Test
	public void testXPath() throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader
				.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/sax_users.xml"));
		// ��·���µ� users/user
		String xpath = "/users/user";

		Element element = (Element) document.selectSingleNode(xpath);
		System.out.println(element.asXML());
	}

	@Test
	public void testXPath2() throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader
				.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/sax_users.xml"));
		// ����·������������ = 9995 �� user Ԫ��
		String xpath = "//user[@*='9995']";
		// String xpath = "//user[@index='9995']";

		Element element = (Element) document.selectSingleNode(xpath);
		System.out.println(element.asXML());
	}

	@Test
	public void testXPath3() throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader
				.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/sax_users.xml"));
		// ����·�������һ�� user
		String xpath = "//user[last()]";

		Element element = (Element) document.selectSingleNode(xpath);
		System.out.println(element.asXML());
		
//		String xpath = "/users/user[@*>9990]";
//		List nodes = document.selectNodes(xpath);
//		for (int i=0; i<nodes.size();i++) {
//			Element element = (Element) nodes.get(i);
//			System.out.println(element.asXML());
//		}
	}

	
/*************************************************** jaxb ********************************************************/
	
	/**
	 * ���� JAXB д�ļ�
	 * @throws Exception
	 */
	@Test
	public void testJAXB() throws Exception {
		Students students = new Students();
		for (int i = 0; i < 10; i++) {
			Student student = new Student();
			student.setName("zhangsan_" + i);
			student.setSchool("���մ�ѧ");
			student.setAge(20 + i);
			students.getStudents().add(student);
		}
		StringWriter writer = new StringWriter();
		JAXBUtil.objectToXml(students, writer);
		System.out.println(writer.toString());

		FileUtil.writeStringToFile(writer.toString(),
				new File("E:\\teacher\\code\\eclipse_workspace\\linkknown\\src\\com\\linkknown\\xml\\students.xml"));
	}

	/**
	 * ���� JAXB ���ļ�
	 * @throws Exception
	 */
	@Test
	public void testJAXB2() throws Exception {
		Reader reader = new BufferedReader(new InputStreamReader(
				XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/students.xml"), "UTF-8"));
		Students students = JAXBUtil.xmlToObject(new Students(), reader);
		System.out.println(students);
	}
	
	
	
/******************************************** �ۺ���ϰ *****************************************************/	
	
	/**
	 * ����ʹ�� dom4j ���� WeatherWebService.xml
	 * @throws DocumentException 
	 */
	@Test
	public void testParseWeatherWSDL () throws DocumentException {
	
	}
	
}
