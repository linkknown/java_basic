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
 * Class 类测试
 * @author Administrator
 *
 */
public class ClassTest {

	/**
	 * 获取Class对象的三种方式
	 * 1 Object ——> getClass();
	 * 2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
	 * 3 通过Class类的静态方法：forName（String  className）(常用)
	 * @throws ClassNotFoundException 
	 *
	 */
	@Test
	public void testClass1 () throws ClassNotFoundException {
		Student student = new Student();
		Class stuClass1 = student.getClass();
		
		Class stuClass2 = Student.class;
		
		Class stuClass3 = Class.forName("com.linkknown.reflect.Student");
	
		// 说明了内存中 Student 类对应的 Class 类只有一个实例
		System.out.println(stuClass1 == stuClass2);
		System.out.println(stuClass2 == stuClass3);
		System.out.println(stuClass1 == stuClass3);
	}
	
	/**
	 * 获取 String 类相关的元数据信息
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testClassForString () throws NoSuchMethodException, SecurityException {
		Class clazz = String.class;
		// 构造器
		Constructor[] constructors = clazz.getConstructors();
		System.out.println("String 类有 " + constructors.length + " 个公有构造方法。");
		constructors = clazz.getDeclaredConstructors();
		System.out.println("String 类有 " + constructors.length + " 个构造方法(包括：私有、受保护、默认、公有)。");
		Constructor constructor = clazz.getConstructor(null);
		System.out.println("String 类的无惨构造器" + constructor);
		
		// 字段
		Field[] fields = clazz.getFields();
		System.out.println("String 类有 " + fields.length + " 个 公有字段。");
		fields = clazz.getDeclaredFields();
		System.out.println("String 类有 " + fields.length + " 个 字段(包括：私有、受保护、默认、公有)。");
		
		// 父类
		Class superclass = clazz.getSuperclass();
		System.out.println("String 类的父类是 " + superclass);
		
		// 父接口
		Class[] interfaces = clazz.getInterfaces();
		for (Class cls : interfaces) {
			System.out.println("String 类的父接口有 " + cls);
		}
	}
	
	/**
	 * 测试创建实例
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
		// 通过反射创建实例
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
	 * 不使用切面编程
	 */
	@Test
	public void testAop1 () {
		KaoShi kaoshi = new KaoShi("zhangsan");
		kaoshi.start();
	}
	
	/**
	 * 使用切片编程
	 */
	@Test
	public void testAop2 () {
		KaoShi2 kaoshi = new KaoShi2("zhangsan");
		InvocationHandler handler = new LogInterceptor(kaoshi);
		// newProxyInstance 必须传代理的父接口
		// 生成的代理类是父接口的一个子类（动态生成的，和被代理类是兄弟类，此处不能用 KaoShi2 接收）
		KaoshiInterface kaoshiInterface = (KaoshiInterface) Proxy.newProxyInstance(KaoShi2.class.getClassLoader(), new Class[] {KaoshiInterface.class}, handler);
		kaoshiInterface.start();
	}
	
	
	/**
	 * 使用切片编程
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
