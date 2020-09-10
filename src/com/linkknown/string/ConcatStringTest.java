package com.linkknown.string;

import org.junit.jupiter.api.Test;

/**
 * �ַ������Ӳ���
 * @author Administrator
 * 1.�������ֱ���á�+���������ַ�������������
 * 2.��ƴ�������ַ�����������4������ʱ��concatЧ������ߵ�
 * 3.����ַ���ƴ�ӵ�ʱ��StringBuilder/StringBuffer��Ч������ѹ��
 * 4.�ڲ���Ҫ�����̰߳�ȫ�����ʱ��ʹ��StringBuilder��Ч�ʱ�StringBuffer����
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
	
	
	/**
	 * �������ӷ�ʽ���ܱȽ�
	 * 1.�������ֱ���á�+���������ַ������������� 
	 * 2.��ƴ�������ַ�����������4������ʱ��concatЧ������ߵ�
	 * 3.����ַ���ƴ�ӵ�ʱ��StringBuilder/StringBuffer��Ч������ѹ��
	 * 4.�ڲ���Ҫ�����̰߳�ȫ�����ʱ��ʹ��StringBuilder��Ч�ʱ�StringBuffer����
	 */
	@Test
	public void testStringConcat () {
		
		// +
		String initialStr = "";
		long start = System.currentTimeMillis();
		for (int i=0; i<100000; i++) {
			initialStr = initialStr + "0";
		}
		long end = System.currentTimeMillis();
		System.out.println(String.format("+ total cost time %d (ms)", end - start));
		
		
		// concat
		initialStr = "";
		start = System.currentTimeMillis();
		for (int i=0; i<100000; i++) {
			initialStr = initialStr.concat("0");
		}
		end = System.currentTimeMillis();
		System.out.println(String.format("concat total cost time %d (ms)", end - start));
		
		
		// StringBuilder
		StringBuilder sb = new StringBuilder();
		start = System.currentTimeMillis();
		for (int i=0; i<100000; i++) {
			sb = sb.append("0");
		}
		end = System.currentTimeMillis();
		System.out.println(String.format("StringBuilder total cost time %d (ms)", end - start));
	
		
		// StringBuffer
		StringBuffer sbf = new StringBuffer();
		start = System.currentTimeMillis();
		for (int i=0; i<100000; i++) {
			sbf = sbf.append("0");
		}
		end = System.currentTimeMillis();
		System.out.println(String.format("StringBuffer total cost time %d (ms)", end - start));
	}
}
