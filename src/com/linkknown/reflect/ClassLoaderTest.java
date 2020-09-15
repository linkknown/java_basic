package com.linkknown.reflect;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.jupiter.api.Test;

public class ClassLoaderTest {
	
	/**
	 * AppClassLoader�ĸ�������ΪExtClassLoader��
	 * ExtClassLoader�ĸ�������Ϊnull(BoopStrap ClassLoder������ͼ�У���Ϊ������C/C++��д�ģ����������������һ���֣�������һ��java�ࡣ)��
	 * BoopStrap ClassLoaderΪ������������ 
	 */
	@Test
	public void testClassLoader() {
		// sun.misc.Launcher$AppClassLoader@2503dbd3
		// sun.misc.Launcher$ExtClassLoader@593634ad
		System.out.println(ClassLoaderTest.class.getClassLoader().toString());
		System.out.println(ClassLoaderTest.class.getClassLoader().getParent().toString());
		System.out.println(ClassLoaderTest.class.getClassLoader().getParent().getParent());		// null
	
		// �ж��Ƿ���ͬһ���������
		System.out.println(String.class.getClassLoader() == Serializable.class.getClassLoader());
	}
	
	/**
	 * ʹ�����������ȡ�����ļ�
	 * @throws IOException 
	 */
	@Test
	public void testReadProperties () throws IOException {
		InputStream inputStream = ClassLoaderTest.class.getClassLoader().getResourceAsStream("com/linkknown/reflect/account.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		String userName = properties.getProperty("userName");
		String password = properties.getProperty("password");
		
		System.out.println(userName);
		System.out.println(password);
		inputStream.close();
	}
	
	/**
	 * Java��λ�ȡ��ǰ��·��
	 */
	@Test
	public void testClassRoot () {
		System.out.println(ClassLoaderTest.class.getResource(""));		// �õ����ǵ�ǰ��ClassLoaderTest.class�ļ���URIĿ¼���������Լ���
		System.out.println(ClassLoaderTest.class.getResource("/"));		// �õ����ǵ�ǰ��classpath�ľ���URI·��
		System.out.println(ClassLoaderTest.class.getResource("/com/linkknown/reflect/account.properties"));
	}
	
	/**
	 * �����Զ����������
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testCustomClassLoader () throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
//		MyClassLoader myClassLoader = new MyClassLoader("D:/HelloWorld.class");
//		Object object = Class.forName("com.linkknown.reflect.HelloWorld", true, myClassLoader).newInstance();
//		System.out.println(object);
		
		
		CustomClassLoader customClassLoader = new CustomClassLoader("D:\\HelloWorld.class");
		
		Object object = Class.forName("HelloWorld", true, customClassLoader).newInstance();
	
		System.out.println(object);
		
		Method printStrMethod = object.getClass().getDeclaredMethod("printStr", String.class);
	
		printStrMethod.invoke(object, "helloworld...");
	}
	
	
	// ���˫��ί��ģ��
}
