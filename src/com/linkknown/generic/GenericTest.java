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
 * 测试泛型
 * 
 * @author Administrator
 *
 */
public class GenericTest {

	/**
	 * 一个泛型类场景
	 */
	@Test
	public void testOne() {
		Holder<String> holder = new Holder<>();
		holder.setObject("helloworld");
		holder.printObject();
	}

	/**
	 * 两个泛型类场景
	 */
	@Test
	public void testTwo() {
		TwoHolder<String, String> twoHolder = new TwoHolder<String, String>("hello", "hello");
		System.out.println(twoHolder.checkEq());

		TwoHolder<String, Integer> twoHolder2 = new TwoHolder<String, Integer>("100", 100);
		System.out.println(twoHolder2.checkEq());
	}

	/**
	 * 多个泛型类场景
	 */
	@Test
	public void testMulti() {
		MultiHolder<String, Integer, Object, Random> multiHolder = new MultiHolder<String, Integer, Object, Random>(
				"linkknown", 10, new Object(), new Random());
		multiHolder.make();
	}

	/**
	 * 泛型类 从 RandomList 中随机获取元素
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
	 * 泛型接口 练习：Fibonacci数列
	 */
	@Test
	public void testGenericInterface() {
		Fibonacci fibonacci = new Fibonacci();
		for (int i = 0; i < 100; i++) {
			System.out.print(fibonacci.next() + " ");
		}
	}

	/**
	 * 泛型方法
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
	 * 可变参数与泛型方法
	 */
	@Test
	public void testGenericArgs() {
		System.out.println(makeList("hello", "world", "hello", "linkknown"));
		System.out.println(makeList(1, 2, 3, 4));
	}
	
	/**
	 * map key， value 互换
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
	 * 测试 Map 泛型 K，V
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
	 * 通配符测试
	 */
	@Test
	public void testQuestion () {
		print(Arrays.asList("hello", 1));
	}
	
//	/**
//	 * 测试泛型上界
//	 */
//	@Test
//	public void testUpperBound () {
//		List<? extends Animal> animals = new ArrayList<>();
////	 	上界<? extends Animal>规定：只能取(get)，不能添加(add)。
//		animals.add(new Bird());	
//		animals.add(new Dog());
//		System.out.println(animals);
//	}
	
	
	// 泛型上界只能取，不能添加
	public void printAnimals (List<? extends Animal> animals) {		
		System.out.println(animals);
	}
	
	/**
	 * 测试泛型上界
	 */
	@Test
	public void testUpperBound2 () {
		List<Animal> animals = new ArrayList<>();	// 使用明确的类型 List<Animal>
		animals.add(new Bird());
		animals.add(new Dog());
		printAnimals(animals);
	}
}
