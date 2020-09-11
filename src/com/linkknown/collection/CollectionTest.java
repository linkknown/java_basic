package com.linkknown.collection;

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
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
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
}









