package com.linkknown.collection;

import java.lang.reflect.Field;
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
	
	/**************************** �˽⼴�ɣ������� *********************************/
	/********************* �βκ�ʵ�ε�ʹ��: ���ϴ��������ô��� *************************/
	public static void add (Integer i) {	
		// Integer ���� value �� final ��
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
	 * �β� ��������ʽ���������ڶ��巽����ʱ��ʹ�õĲ��������������յ����ߴ��ݵĲ����ġ� 
	 * �β�ֻ���ڷ��������õ�ʱ��������Ż�����ڴ浥Ԫ���ڷ������ý���֮�����ͷ���������ڴ浥Ԫ�� 
	 * ���,�β�ֻ�ڷ����ڲ���Ч������������ö���ĸĶ�Ҳ�޷�Ӱ�쵽�����⡣
	 * 
	 * ʵ�� ������ʵ�ʲ��������ڵ���ʱ���ݸ������Ĳ�����ʵ���ڴ��ݸ���ķ���֮ǰ��Ҫ��Ԥ�ȸ�ֵ�ġ�
	 * 
	 * 
	 * ֵ���ݣ���������ʱ��ʵ�ʲ���������ֵ���ݸ���Ӧ����ʽ�������������յ���ԭʼֵ��һ��copy�� 
	 * ��ʱ�ڴ��д���������ȵĻ������ͣ���ʵ�ʲ�������ʽ���������淽���еĲ������Ƕ��β����ֵ���޸ģ���Ӱ��ʵ�ʲ�����ֵ��
	 * 
	 * ���ô��ݣ�Ҳ��Ϊ��ַ���ݡ�ַ���ݡ���������ʱ��ʵ�ʲ���������(��ַ�������ǲ�����ֵ)�����ݸ����������Ӧ����ʽ������
	 * �������յ���ԭʼֵ���ڴ��ַ�ڷ���ִ���У��βκ�ʵ��������ͬ��ָ��ͬһ���ڴ��ַ������ִ���ж����õĲ�������Ӱ�쵽ʵ�ʶ���
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









