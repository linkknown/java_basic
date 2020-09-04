package com.linkknown.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * 
 * ʹ�÷��ͳ���
 * @author Administrator
 *
 */
public class UsingGenericTest {

	/**
	 * 1. ���Ԫ�ش洢�İ�ȫ������
	 * 2. �����ȡ����Ԫ��ʱ����Ҫ����ǿת������
	 */
	@Test
	public void testUsingGeneric () {
		List<Bird> lst = new ArrayList<>();
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
//		lst.add(new Animal());
	
		for (int i=0; i<lst.size(); i++) {
			Bird bird = lst.get(i);
			bird.fly();
		}
	}
	
	
	private <T> T generateRandom (T[] objectArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(objectArr.length);
		return objectArr[randomIndex];
	}
	
	/**
	 * 1. ���Ԫ�ش洢�İ�ȫ������
	 * 2. �����ȡ����Ԫ��ʱ����Ҫ����ǿת������
	 */
	@Test
	public void testNoGeneric3 () {
		Bird bird = generateRandom(new Bird[] {new Bird(), new Bird()});
		bird.fly();
		
		String str = generateRandom(new String[] {"hello", "world"});
		System.out.println(str);
	}
	
	/**
	 * �˴��� HelloWorld Ҳ�Ƿ���,��һ���ǵ��� �� T K V E
	 * ֻ�ǽ���ʹ��  �� T K V E Լ����ͨ���׶�
	 * @param <HelloWorld>
	 * @param objectArr
	 * @return
	 */
	private <HelloWorld> HelloWorld generateRandom2 (HelloWorld[] objectArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(objectArr.length);
		return objectArr[randomIndex];
	}
	
	/**
	 * ʹ�ô�д��ĸA,B,C,D......X,Y,Z����ģ��Ͷ��Ƿ��ͣ���T����AҲһ��������Tֻ�������ϵ��������
	 */
	@Test
	public void testNoGeneric4 () {
		Bird bird = generateRandom2(new Bird[] {new Bird(), new Bird()});
		bird.fly();
	}
}
