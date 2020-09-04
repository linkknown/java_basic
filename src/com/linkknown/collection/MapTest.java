package com.linkknown.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * map 测试
 * @author Administrator
 *
 */
public class MapTest {

	/**
	 * 统计数字出现的次数
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
	 * 测试 map 常见方法
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
}
