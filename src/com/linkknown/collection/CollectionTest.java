package com.linkknown.collection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * ���ϲ���
 * @author 38909
 *
 */
public class CollectionTest {

	
	/**
	 * ��������ͼ��ϵ�����
	 * 1�����鳤�ȹ̶������ϵĳ��Ȳ��̶�
	 * 2�����鳤��ʹ�õ��� length ����,���ϵĳ���ʹ�õ��� size() ����
	 * ��������ǻ�������,����ֻ��������������
	 */
	@Test
	public void testCollection () {
		char[] chars = "helloworld".toCharArray();
		System.out.println(chars.length);
		
		// ѭ�� 100 ��,�������һ����,�洢���е�ż��
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
	 * ֤�������з����Ԫ���Ƕ�������ã������Ƕ�����
	 */
	@Test
	public void testCollection2 () {
		List<Person> personList01 = new ArrayList<>();
		List<Person> personList02 = new ArrayList<>();
		
		Person person = new Person();
		person.setName("tom");
		person.setAge(20);
		
		// �������Ϸ�����ͬ��Ԫ��
		personList01.add(person);
		personList02.add(person);
		
		System.out.println(personList01);
		System.out.println(personList02);
		
		// �޸�����һ������
		for (Person pson : personList02) {
			pson.setName("bob");
			pson.setAge(30);
		}
		
		// ��һ�����ϸ��Ÿı�
		System.out.println(personList01);
		// ���϶�����д�� toString ����
		System.out.println(personList02);
	}
	
	
	/**
	 * ���Ͽ��Դ�Ų�ͬ���͵�����,���ǽ���洢��ͬ����,������洢��ͬ����
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









