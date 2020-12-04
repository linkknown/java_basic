package com.linkknown.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ��һ�� T ���͵�Ԫ���������ȡ����
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
