package com.linkknown.collection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * 集合测试
 * @author 38909
 *
 */
public class CollectionTest {

	
	/**
	 * 测试数组和集合的区别
	 * 1、数组长度固定，集合的长度不固定
	 * 2、数组长度使用的是 length 属性,集合的长度使用的是 size() 方法
	 * 数组可以是基本类型,集合只可以是引用类型
	 */
	@Test
	public void testCollection () {
		char[] chars = "helloworld".toCharArray();
		System.out.println(chars.length);
		
		// 循环 100 次,随机产生一个数,存储所有的偶数
		List<Integer> evenNumberList = new ArrayList<>(); 
		Random random = new Random();
		for (int i=0; i<100; i++) {
			int number = random.nextInt(100);
			if (number % 2 == 0) {
				evenNumberList.add(number);
			}
		}
		System.out.println(evenNumberList.size());
	}
	
	/**
	 * 证明集合中放入的元素是对象的引用，而不是对象本身
	 */
	@Test
	public void testCollection2 () {
		List<Person> personList01 = new ArrayList<>();
		List<Person> personList02 = new ArrayList<>();
		
		Person person = new Person();
		person.setName("tom");
		person.setAge(20);
		
		// 两个集合放入相同的元素
		personList01.add(person);
		personList02.add(person);
		
		System.out.println(personList01);
		System.out.println(personList02);
		
		// 修改其中一个集合
		for (Person pson : personList02) {
			pson.setName("bob");
			pson.setAge(30);
		}
		
		// 另一个集合跟着改变
		System.out.println(personList01);
		// 集合对象重写了 toString 方法
		System.out.println(personList02);
	}
	
	
	/**
	 * 集合可以存放不同类型的数据,但是建议存储相同类型,不建议存储不同类型
	 */
	@Test
	public void testCollection3 () throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<Integer> lst = new ArrayList<>();
		
		// boolean add(E e);
		lst.add(10);
		
		Method add = lst.getClass().getDeclaredMethod("add", Object.class);
		add.invoke(lst, 'a');
		
		System.out.println(lst);
	}
	
	/**************************** 了解即可，不讲解 *********************************/
	/********************* 形参和实参的使用: 集合传参是引用传递 *************************/
	public static void add (Integer i) {	
		// Integer 里面 value 是 final 的
		// private final int value;
		i = i + 1;
	}
	
	public static void add2 (Integer i) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = Integer.class.getDeclaredField("value");
		field.setAccessible(true);
		field.set(i, i + 1);
	}
	
	public static void add (List<Integer> lst) {
		Integer i = lst.get(0);

		lst.set(0, lst.get(0) + 1);
		
		System.out.println(lst);
	}
	
	/**
	 * 形参 ：就是形式参数，用于定义方法的时候使用的参数，是用来接收调用者传递的参数的。 
	 * 形参只有在方法被调用的时候，虚拟机才会分配内存单元，在方法调用结束之后便会释放所分配的内存单元。 
	 * 因此,形参只在方法内部有效，所以针对引用对象的改动也无法影响到方法外。
	 * 
	 * 实参 ：就是实际参数，用于调用时传递给方法的参数。实参在传递给别的方法之前是要被预先赋值的。
	 * 
	 * 
	 * 值传递：方法调用时，实际参数把它的值传递给对应的形式参数，函数接收的是原始值的一个copy， 
	 * 此时内存中存在两个相等的基本类型，即实际参数和形式参数，后面方法中的操作都是对形参这个值的修改，不影响实际参数的值。
	 * 
	 * 引用传递：也称为地址传递、址传递。方法调用时，实际参数的引用(地址，而不是参数的值)被传递给方法中相对应的形式参数，
	 * 函数接收的是原始值的内存地址在方法执行中，形参和实参内容相同，指向同一块内存地址，方法执行中对引用的操作将会影响到实际对象。
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	@Test
	public void testCollection4 () throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Integer i = 10;
		
		add(i);
		
		System.out.println(i);
		
		add2(i);
		
		System.out.println(i);
		
		List<Integer> lst = new ArrayList<>();
		
		lst.add(i);
		
		add(lst);
		
		System.out.println(lst);
	}
}









