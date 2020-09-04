package com.linkknown.string;

import org.junit.jupiter.api.Test;

/**
 * �ַ������Ӳ���
 * @author Administrator
 *
 */
public class ConcatStringTest {
	
	/**
	 * + ������
	 */
	@Test
	public void testAdd () {
		System.out.println("hello" + "world");
		System.out.println("hello" + 'a');
		System.out.println("hello" + 12);
	}
	
	/**
	 * concat ����
	 */
	@Test
	public void testConcat () {
		System.out.println("hello".concat("world"));
	}
	
	/**
	 * StringBuilder ��,�̲߳���ȫ��Ч�ʸ��� StringBuffer
	 */
	@Test
	public void testStringBuilder () {
		StringBuilder sb = new StringBuilder();
		sb.append("hello");
		sb.append("world");
		sb.append("hello");
		sb.append("world...");
		System.out.println(sb.toString());
	}
	
	/**
	 * StringBuffer ��,�̰߳�ȫ
	 */
	@Test
	public void testStringBuffer () {
		StringBuffer sb = new StringBuffer();
		sb.append("hello");
		sb.append("world");
		sb.append("hello");
		sb.append("world...");
		System.out.println(sb.toString());
	}
	
}
