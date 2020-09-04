package com.linkknown.string;

import org.junit.jupiter.api.Test;

/**
 * 字符串 api 测试类
 * @author Administrator
 *
 */
public class StringAPITest {
	
	/**
	 * 测试字符串常用 api
	 */
	@Test
	public void testStringAIP () {
		String str = "helloworld";
		String str2 = "             helloworld";
		
		System.out.println(str.length());
		System.out.println(str.charAt(0));
		System.out.println(str.getBytes().length);
		System.out.println(str.toCharArray().length);
		System.out.println(str.equals("helloworld"));
		System.out.println(str.equalsIgnoreCase("helloWORLD"));
		System.out.println(str.contains("helloworld"));
		System.out.println(str.startsWith("hello"));
		System.out.println(str.endsWith("world"));
		System.out.println(str.split("o").length);
		System.out.println(str.replace("o", "O"));
		System.out.println(str2);
		System.out.println(str2.trim());
	}
	
	/**
	 * 测试字符串格式化
	 */
	@Test
	public void testStringFormat () {
		System.out.println(String.format("%s 的年龄是 %d", "tom", 20));
	}
}
