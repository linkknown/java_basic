package com.linkknown.collection;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * map ����
 * @author Administrator
 *
 */
public class MapTest {

	/**
	 * �������10000����0~20�����֣�ͳ�����ֳ��ֵĴ���
	 */
	@Test
	public void testHashMap () {
		Random random = new Random();
		
		Map<Integer, Integer> map = new HashMap<>();
		for (int i=0; i<10000; i++) {
			int randomNum = random.nextInt(20);
			
			Integer randomNumCount = map.get(randomNum);
			
			map.put(randomNum, randomNumCount != null ? ++randomNumCount : 1);
		}

		System.out.println(map);
	}
	
	/**
	 * ���� map ��������
	 */
	@Test
	public void testHashMap2 () {
		
		Map<String, String> map = new HashMap<>();
		map.put("tom", "tom123");
		map.put("bob", "bob123");

		System.out.println(map.containsKey("tom"));
		System.out.println(map.containsValue("tom123"));
		System.out.println(map.get("tom"));
		System.out.println(map.getOrDefault("smith", "smith"));
		System.out.println(map.isEmpty());
		System.out.println(map.size());
	}
	
	/**
	 * ��֤ HashMap ��������
	 */
	@Test
	public void testHashMap3 () {
		HashMap<String, String> map = new HashMap<>();
		for (int i=0; i<1000; i++) {
			map.put("test_" + i, "test_" + i);
		}
		
		// map ��д�� toString ����
		System.out.println(map);
	}

	/**
	 * ��֤ LinkedHashMap ��������
	 */
	@Test
	public void testHashMap4 () {
		HashMap<String, String> map = new LinkedHashMap<>();
		for (int i=0; i<1000; i++) {
			map.put("test_" + i, "test_" + i);
		}
		
		System.out.println(map);
	}
	
	@Test
	public void testForeach () {
		Map<String,String> map = new LinkedHashMap<>();
		for (int i = 0; i<100; i++) {
			map.put("test_" + i, "test_" + i);
		}
		
		// map ��֧����ͨ for ѭ��
//		for (int i=0; i<map.size(); i++) {
//			map.get(i);
//		}
	
		Set<Entry<String, String>> entrySet = map.entrySet();
		for (Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey() + "~" +entry.getValue());
		}
		
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "~" +entry.getValue());
		}
		
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey() + "~" +entry.getValue());
		}

		map.entrySet().forEach(entry -> System.out.println(entry.getKey() + "~" + entry.getValue()));
	}
	
	@Test
	public void testHashMap5 () {
		HashMap<String, String> map = new LinkedHashMap<>();
		for (int i=0; i<1000; i++) {
			map.put("test_" + i, "test_" + i);
		}
		
		// ���� Set ��ʾ key �ǲ��ظ���
		Set<String> keys = map.keySet();
		System.out.println(keys);
		
		// ���� Collection ��ʾ value �ǿ��ظ���
		Collection<String> values = map.values();
		System.out.println(values);
		
		
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		Iterator<String> iterator2 = values.iterator();
		while (iterator2.hasNext()) {
			System.out.println(iterator2.next());
		}
	}
	
	/**
	 * Enumeration ��ʹ��
	 */
	@Test
	public void testHashMap6 () {
//		Map<String,String> map = new Hashtable<>();
		Hashtable<String,String> map = new Hashtable<>();
		for (int i=0; i<1000; i++) {
			map.put("test_" + i, "test#" + i);
		}
		
		// ʹ�� Enumeration ���� key
		Enumeration<String> keyEnumeration = map.keys();
		while (keyEnumeration.hasMoreElements()) {
			String element = keyEnumeration.nextElement();
			System.out.println(element);
		}
		
		// ʹ�� Enumeration ���� value
		Enumeration<String> valueEnumeration = map.elements();
		while (valueEnumeration.hasMoreElements()) {
			String element = valueEnumeration.nextElement();
			System.out.println(element);
		}
	}
}










