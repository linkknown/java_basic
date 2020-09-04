package com.linkknown.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import com.linkknown.util.FileUtil;
import com.linkknown.util.JAXBUtil;

public class XMLTest {

	/**
	 * dom ��ʽ��ȡ xml
	 * 
	 * @throws DocumentException
	 */
	@Test
	public void testReadXML() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader
				.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/users.xml"));
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
				.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/users.xml"));
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
			String index = userElement.attributeValue("index");

			User user = new User();
			user.setName(nameElement.getStringValue());
			user.setPassword(passwordElement.getStringValue());
			user.setIndex(Integer.valueOf(index));
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
			user.setIndex(i);
			user.setName("tom_" + i);
			user.setPassword("password_" + i);
			users.add(user);
		}

		System.out.println(users);

		// ���� document
		Document document = DocumentHelper.createDocument();
		// �������ڵ�
		Element rootElement = document.addElement("users");
		for (User user : users) {
			Element userElement = rootElement.addElement("user");
			userElement.setAttributeValue("index", user.getIndex() + "");
			Element nameElement = userElement.addElement("name");
			nameElement.setText(user.getName());
			Element passwordElement = userElement.addElement("password");
			passwordElement.setText(user.getPassword());
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
						"E:\\teacher\\code\\eclipse_workspace\\linkknown\\src\\com\\linkknown\\xml\\users1.xml"),
				xmlFormat);
		writer.write(document);
		writer.close();
	}

	/**
	 * SAX ��ʽ��ȡ��������������ʱ���ļ����ܴ��� 500 M,������ dom ��ʽ��ȡ��ܳԿ�
	 * 
	 * @throws DocumentException
	 */
	@Test
	public void testReadXML3() throws DocumentException {
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

		reader.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/users2.xml"));
	}

	@Test
	public void testXPath() throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader
				.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/users2.xml"));
		// ��·���µ� users/user
		String xpath = "/users/user";

		Element element = (Element) document.selectSingleNode(xpath);
		System.out.println(element.asXML());
	}

	@Test
	public void testXPath2() throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader
				.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/users2.xml"));
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
				.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/users2.xml"));
		// ����·�������һ�� user
		String xpath = "//user[last()]";

		Element element = (Element) document.selectSingleNode(xpath);
		System.out.println(element.asXML());
	}

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
}
