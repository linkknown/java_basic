package com.linkknown.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * ���Բ�ʹ�÷��ͻ��ƣ�JDK1.4��
 * @author Administrator
 *
 */
public class NoGenericTest {
	
	/**
	 * 1. ���Ԫ�ش洢�İ�ȫ������
	 * 2. �����ȡ����Ԫ��ʱ����Ҫ����ǿת������
	 */
	@Test
	public void testNoGeneric () {
		List lst = new ArrayList();
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
//		lst.add(new Animal());
	
		for (int i=0; i<lst.size(); i++) {
			Object object = lst.get(i);
			Bird bird = (Bird) object;
			bird.fly();
		}
	}
	
	
	private String generateRandom (String[] strArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(strArr.length);
		return strArr[randomIndex];
	}
	
	private Integer generateRandom (Integer[] intArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(intArr.length);
		return intArr[randomIndex];
	}
	
	private Bird generateRandom (Bird[] birdArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(birdArr.length);
		return birdArr[randomIndex];
	}
	
	/**
	 * generateRandom �������ع���,��ͨ��
	 */
	@Test
	public void testNoGeneric2 () {
		System.out.println(generateRandom(new String[] {"hello", "world"}));
		System.out.println(generateRandom(new Integer[] {1, 2}));
		System.out.println(generateRandom(new Bird[] {new Bird(), new Bird()}));
	}
	
	private Object generateRandomAdjust (Object[] objectArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(objectArr.length);
		return objectArr[randomIndex];
	}
	
	/**
	 * ���ǵ�ǿת���޷���֤���Ͱ�ȫ
	 */
	@Test
	public void testNoGeneric3 () {
		Object obj = generateRandomAdjust(new Bird[] {new Bird(), new Bird()});
		Bird bird = (Bird) obj;
		bird.fly();
	}
}
