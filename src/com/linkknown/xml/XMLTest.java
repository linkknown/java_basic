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
	 * 如何手动写一个 xml：练习 demo.xml 的编写
	 */
	
/*************************************************** dom4j ********************************************************/	
	
	/**
	 * dom 方式读取 xml
	 * 
	 * @throws DocumentException
	 */
	@Test
	public void testReadXML() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader
				.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/dom_users.xml"));
		// asXML 以字符串形式显示
		System.out.println(document.asXML());
	}

	/**
	 * dom 方式读取 xml
	 * 
	 * @throws DocumentException
	 */
	@Test
	public void testReadXML2() throws DocumentException {
		List<User> users = new ArrayList<User>();

		// 读取 xml 文件, 得到 document 文档对象
		SAXReader reader = new SAXReader();
		Document document = reader
				.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/dom_users.xml"));
		// 获取根元素
		Element rootElement = document.getRootElement();
		// 获取所有 user 子元素
		List elements = rootElement.elements("user");
		Iterator iterator = elements.iterator();
		while (iterator.hasNext()) {
			Element userElement = (Element) iterator.next();
			// 获取子元素
			Element nameElement = userElement.element("name");
			Element passwordElement = userElement.element("password");
			// 获取属性值
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
	 * dom 方式写入 xml
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
		users.get(0).setPassword("<!-- 我是注释 -->");
		users.get(1).setPassword("<>&'\"");
		users.get(2).setPassword("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<demo>\n我是嵌套的xml\n</demo>");
		users.get(3).setPassword("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<demo>\n我是嵌套的xml\n</demo>");
		
		System.out.println(users);

		// 创建 document
		Document document = DocumentHelper.createDocument();
		// 创建根节点
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
		// 设置文件编码
		xmlFormat.setEncoding("UTF-8");
		// 设置换行
		xmlFormat.setNewlines(true);
		// 生成缩进
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
	 * SAX 方式读取，当数据量过大时，文件可能大于 500 M,这样用 dom 方式读取会很吃亏
	 * 
	 * @throws DocumentException
	 */
	@Test
	public void testSAXRead() throws DocumentException {
		SAXReader reader = new SAXReader();
		// 第一个参数是 xpath
		reader.addHandler("/users/user", new ElementHandler() {

			@Override
			public void onStart(ElementPath path) {
				System.out.println("开始扫描了...");
			}

			@Override
			public void onEnd(ElementPath path) {

				Element userElement = path.getCurrent();
				if ("tom_9998".equals(userElement.element("name").getTextTrim())) {
					System.out.println(userElement.element("password").getTextTrim());
				}
				// 从内存中移去
				userElement.detach();
			}
		});

		reader.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/sax_users.xml"));
	}

	/**
	 * SAX 方式写 xml
	 * @throws TransformerConfigurationException 
	 * @throws SAXException 
	 * @throws IOException 
	 */
	@Test
	public void testSAXWrite () throws TransformerConfigurationException, SAXException, IOException {
		// 生成一个SAXTransformFacotry 对象，获取工厂，并设置初始参数
		SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		// 通过SAXTransformFacotry 对象创建一个TransformerHandler 对象，获取句柄
		TransformerHandler handler = factory.newTransformerHandler();
		// 通过TransformerHandler 对象创建一个transformer 对象
		Transformer transformer = handler.getTransformer();
		// 通过transformer 可以设置输出格式, 注意必须在hanlder 设置result之前设置才有效
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");			//省略XML声明

		OutputStream outputStream = new FileOutputStream("D:\\zhourui\\program\\java\\IDEA\\java_basic\\src\\com\\linkknown\\xml\\sax_write_users.xml");
		// 创建一个Result 对象 并且将其与handler 对象关联起来
		handler.setResult(new StreamResult(outputStream));
		
		// 利用hanlder 对象对xml 进行编写
		// 打开文档
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
		// 根路径下的 users/user
		String xpath = "/users/user";

		Element element = (Element) document.selectSingleNode(xpath);
		System.out.println(element.asXML());
	}

	@Test
	public void testXPath2() throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader
				.read(XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/sax_users.xml"));
		// 任意路径下任意属性 = 9995 的 user 元素
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
		// 任意路径下最后一个 user
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
	 * 测试 JAXB 写文件
	 * @throws Exception
	 */
	@Test
	public void testJAXB() throws Exception {
		Students students = new Students();
		for (int i = 0; i < 10; i++) {
			Student student = new Student();
			student.setName("zhangsan_" + i);
			student.setSchool("安徽大学");
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
	 * 测试 JAXB 读文件
	 * @throws Exception
	 */
	@Test
	public void testJAXB2() throws Exception {
		Reader reader = new BufferedReader(new InputStreamReader(
				XMLTest.class.getClassLoader().getResourceAsStream("com/linkknown/xml/students.xml"), "UTF-8"));
		Students students = JAXBUtil.xmlToObject(new Students(), reader);
		System.out.println(students);
	}
	
	
	
/******************************************** 综合练习 *****************************************************/	
	
	/**
	 * 尝试使用 dom4j 解析 WeatherWebService.xml
	 * @throws DocumentException 
	 */
	@Test
	public void testParseWeatherWSDL () throws DocumentException {
	
	}
	
}
