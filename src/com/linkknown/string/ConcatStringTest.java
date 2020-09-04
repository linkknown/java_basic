package com.linkknown.string;

import org.junit.jupiter.api.Test;

/**
 * 字符串连接测试
 * @author Administrator
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
	
}
