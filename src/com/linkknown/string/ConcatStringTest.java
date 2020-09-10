package com.linkknown.string;

import org.junit.jupiter.api.Test;

/**
 * 字符串连接测试
 * @author Administrator
 * 1.无论如何直接用“+”号连接字符串都是最慢的
 * 2.在拼接少数字符串（不超过4个）的时候，concat效率是最高的
 * 3.多个字符串拼接的时候，StringBuilder/StringBuffer的效率是碾压的
 * 4.在不需要考虑线程安全问题的时候，使用StringBuilder的效率比StringBuffer更高
 *
 */
public class ConcatStringTest {
	
	/**
	 * + 号链接
	 */
	@Test
	public void testAdd () {
		System.out.println("hello" + "world");
		System.out.println("hello" + 'a');
		System.out.println("hello" + 12);
	}
	
	/**
	 * concat 链接
	 */
	@Test
	public void testConcat () {
		System.out.println("hello".concat("world"));
	}
	
	/**
	 * StringBuilder 类,线程不安全，效率高于 StringBuffer
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
	 * StringBuffer 类,线程安全
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
	 * 多种连接方式性能比较
	 * 1.无论如何直接用“+”号连接字符串都是最慢的 
	 * 2.在拼接少数字符串（不超过4个）的时候，concat效率是最高的
	 * 3.多个字符串拼接的时候，StringBuilder/StringBuffer的效率是碾压的
	 * 4.在不需要考虑线程安全问题的时候，使用StringBuilder的效率比StringBuffer更高
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
