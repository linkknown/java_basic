package com.linkknown.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * ���Է���
 * 
 * @author Administrator
 *
 */
public class GenericTest {

	/**
	 * һ�������ೡ��
	 */
	@Test
	public void testOne() {
		Holder<String> holder = new Holder<>();
		holder.setObject("helloworld");
		holder.printObject();
	}

	/**
	 * ���������ೡ��
	 */
	@Test
	public void testTwo() {
		TwoHolder<String, String> twoHolder = new TwoHolder<String, String>("hello", "hello");
		System.out.println(twoHolder.checkEq());

		TwoHolder<String, Integer> twoHolder2 = new TwoHolder<String, Integer>("100", 100);
		System.out.println(twoHolder2.checkEq());
	}

	/**
	 * ��������ೡ��
	 */
	@Test
	public void testMulti() {
		MultiHolder<String, Integer, Object, Random> multiHolder = new MultiHolder<String, Integer, Object, Random>(
				"linkknown", 10, new Object(), new Random());
		multiHolder.make();
	}

	/**
	 * ������ �� RandomList �������ȡԪ��
	 */
	@Test
	public void testGenericClass() {
		RandomList<String> randomList = new RandomList<>();
		for (String s : "hello world hello linkknown".split(" ")) {
			randomList.add(s);
		}
		for (int i = 0; i < 100; i++) {
			System.out.println(randomList.select());
		}
	}

	/**
	 * ���ͽӿ� ��ϰ��Fibonacci����
	 */
	@Test
	public void testGenericInterface() {
		Fibonacci fibonacci = new Fibonacci();
		for (int i = 0; i < 100; i++) {
			System.out.print(fibonacci.next() + " ");
		}
	}

	/**
	 * ���ͷ���
	 */
	@Test
	public void testGenericMethod() {
		ClassDesc classDesc = new ClassDesc();
		classDesc.getClassName(new String("helloworld"));
		ClassDesc.getClassName2(new Object());
	}

	public static <T> List<T> makeList(T... args) {
		List<T> lst = new ArrayList<>();
		for (T item : args) {
			lst.add(item);
		}
		return lst;
	}

	/**
	 * �ɱ�����뷺�ͷ���
	 */
	@Test
	public void testGenericArgs() {
		System.out.println(makeList("hello", "world", "hello", "linkknown"));
		System.out.println(makeList(1, 2, 3, 4));
	}
	
	/**
	 * map key�� value ����
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return
	 */
	public <K,V> Map<V,K> changeMap (Map<K, V> map) {
		Map<V, K> _map = new HashMap<V, K>();
		Iterator<Entry<K, V>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<K, V> entry = (Map.Entry<K, V>) iterator.next();
			_map.put(entry.getValue(), entry.getKey());
		}
		return _map;
		
	}

	/**
	 * ���� Map ���� K��V
	 */
	@Test
	public void testGenericKV() {
		Map<String, Integer> map = new HashMap<>();
		map.put("tom", 20);
		map.put("bob", 22);
		Map<Integer, String> resultMap = changeMap(map);
		System.out.println(resultMap);
	}
	
	
	public void print (List<?> lst) {
		System.out.println(lst);
	}
	
	/**
	 * ͨ�������
	 */
	@Test
	public void testQuestion () {
		print(Arrays.asList("hello", 1));
	}
	
//	/**
//	 * ���Է����Ͻ�
//	 */
//	@Test
//	public void testUpperBound () {
//		List<? extends Animal> animals = new ArrayList<>();
////	 	�Ͻ�<? extends Animal>�涨��ֻ��ȡ(get)���������(add)��
//		animals.add(new Bird());	
//		animals.add(new Dog());
//		System.out.println(animals);
//	}
	
	
	// �����Ͻ�ֻ��ȡ���������
	public void printAnimals (List<? extends Animal> animals) {		
		System.out.println(animals);
	}
	
	/**
	 * ���Է����Ͻ�
	 */
	@Test
	public void testUpperBound2 () {
		List<Animal> animals = new ArrayList<>();	// ʹ����ȷ������ List<Animal>
		animals.add(new Bird());
		animals.add(new Dog());
		printAnimals(animals);
	}
}
