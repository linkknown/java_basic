package com.linkknown.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 从一组 T 类型的元素中随机获取对象
 * @author Administrator
 *
 */
public class RandomList<T> {

	private List<T> storage = new ArrayList<>();
	
	private Random random = new Random();
	
	public void add(T item) {
		storage.add(item);
	}
	
	public T select () {
		return storage.get(random.nextInt(storage.size()));
	} 
}
