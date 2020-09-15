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
	 * AppClassLoader的父加载器为ExtClassLoader，
	 * ExtClassLoader的父加载器为null(BoopStrap ClassLoder不在上图中，因为它是由C/C++编写的，它本身是虚拟机的一部分，并不是一个java类。)，
	 * BoopStrap ClassLoader为顶级加载器。 
	 */
	@Test
	public void testClassLoader() {
		// sun.misc.Launcher$AppClassLoader@2503dbd3
		// sun.misc.Launcher$ExtClassLoader@593634ad
		System.out.println(ClassLoaderTest.class.getClassLoader().toString());
		System.out.println(ClassLoaderTest.class.getClassLoader().getParent().toString());
		System.out.println(ClassLoaderTest.class.getClassLoader().getParent().getParent());		// null
	
		// 判断是否是同一个类加载器
		System.out.println(String.class.getClassLoader() == Serializable.class.getClassLoader());
	}
	
	/**
	 * 使用类加载器读取配置文件
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
	 * Java如何获取当前类路径
	 */
	@Test
	public void testClassRoot () {
		System.out.println(ClassLoaderTest.class.getResource(""));		// 得到的是当前类ClassLoaderTest.class文件的URI目录。不包括自己！
		System.out.println(ClassLoaderTest.class.getResource("/"));		// 得到的是当前的classpath的绝对URI路径
		System.out.println(ClassLoaderTest.class.getResource("/com/linkknown/reflect/account.properties"));
	}
	
	/**
	 * 测试自定义类加载器
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
	
	
	// 理解双亲委派模型
}
