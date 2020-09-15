package com.linkknown.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Array;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Class �����
 * @author Administrator
 *
 */
public class ClassTest {

	/**
	 * ��ȡClass��������ַ�ʽ
	 * 1 Object ����> getClass();
	 * 2 �κ��������ͣ����������������ͣ�����һ������̬����class����
	 * 3 ͨ��Class��ľ�̬������forName��String  className��(����)
	 * @throws ClassNotFoundException 
	 *
	 */
	@Test
	public void testClass1 () throws ClassNotFoundException {
		Student student = new Student();
		Class stuClass1 = student.getClass();
		
		Class stuClass2 = Student.class;
		
		Class stuClass3 = Class.forName("com.linkknown.reflect.Student");
	
		// ˵�����ڴ��� Student ���Ӧ�� Class ��ֻ��һ��ʵ��
		System.out.println(stuClass1 == stuClass2);
		System.out.println(stuClass2 == stuClass3);
		System.out.println(stuClass1 == stuClass3);
	}
	
	/**
	 * ��ȡ String ����ص�Ԫ������Ϣ
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testClassForString () throws NoSuchMethodException, SecurityException {
		Class clazz = String.class;
		// ������
		Constructor[] constructors = clazz.getConstructors();
		System.out.println("String ���� " + constructors.length + " �����й��췽����");
		constructors = clazz.getDeclaredConstructors();
		System.out.println("String ���� " + constructors.length + " �����췽��(������˽�С��ܱ�����Ĭ�ϡ�����)��");
		Constructor constructor = clazz.getConstructor(null);
		System.out.println("String ����޲ҹ�����" + constructor);
		
		// �ֶ�
		Field[] fields = clazz.getFields();
		System.out.println("String ���� " + fields.length + " �� �����ֶΡ�");
		fields = clazz.getDeclaredFields();
		System.out.println("String ���� " + fields.length + " �� �ֶ�(������˽�С��ܱ�����Ĭ�ϡ�����)��");
		
		// ����
		Class superclass = clazz.getSuperclass();
		System.out.println("String ��ĸ����� " + superclass);
		
		// ���ӿ�
		Class[] interfaces = clazz.getInterfaces();
		for (Class cls : interfaces) {
			System.out.println("String ��ĸ��ӿ��� " + cls);
		}
	}
	
	/**
	 * ���Դ���ʵ��
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	@Test
	public void testNewInstance () throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		// ͨ�����䴴��ʵ��
		Class<?> clazz = Class.forName("com.linkknown.reflect.Student");
		Object instance = clazz.getConstructor().newInstance();
		Field nameField = clazz.getDeclaredField("name");
		nameField.setAccessible(true);
		nameField.set(instance, "zhangsan");
//		Field ageField = clazz.getDeclaredField("age");
//		ageField.setAccessible(true);
//		ageField.set(instance, 20);
		
		Method ageMethod = clazz.getDeclaredMethod("setAge", int.class);
		ageMethod.invoke(instance, 20);
		System.out.println(instance);
	}
	
	/**
	 * ��ʹ��������
	 */
	@Test
	public void testAop1 () {
		KaoShi kaoshi = new KaoShi("zhangsan");
		kaoshi.start();
	}
	
	/**
	 * ʹ����Ƭ���
	 */
	@Test
	public void testAop2 () {
		KaoShi2 kaoshi = new KaoShi2("zhangsan");
		InvocationHandler handler = new LogInterceptor(kaoshi);
		// newProxyInstance ���봫����ĸ��ӿ�
		// ���ɵĴ������Ǹ��ӿڵ�һ�����ࣨ��̬���ɵģ��ͱ����������ֵ��࣬�˴������� KaoShi2 ���գ�
		KaoshiInterface kaoshiInterface = (KaoshiInterface) Proxy.newProxyInstance(KaoShi2.class.getClassLoader(), new Class[] {KaoshiInterface.class}, handler);
		kaoshiInterface.start();
	}
	
	
	/**
	 * ʹ����Ƭ���
	 */
	@Test
	public void testAop3 () {
		KaoShi3 kaoshi = new KaoShi3("zhangsan");
		InvocationHandler handler = new LogInterceptor(kaoshi);
		KaoshiInterface kaoshiInterface = (KaoshiInterface) Proxy.newProxyInstance(KaoShi2.class.getClassLoader(), new Class[] {KaoshiInterface.class}, handler);
		KaoShi3.start(kaoshiInterface);
//		KaoShi3.start(kaoshi);
	}

	@Test
	public void testAop4 () {
		String str = new String("helloworld");
		InvocationHandler handler = new LogInterceptor(str);
		CharSequence proxyInstance = (CharSequence) Proxy.newProxyInstance(KaoShi2.class.getClassLoader(), new Class[] {CharSequence.class}, handler);
		System.out.println(proxyInstance.toString());
	} 
}
