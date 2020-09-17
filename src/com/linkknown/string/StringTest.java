package com.linkknown.string;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * 字符串 api 测试类
 * @author Administrator
 *
 */
public class StringTest {
	
	/**
	 * 测试字符串转义
	 */
	@Test
	public void testTransformString () {
		System.out.println("\"");			// 返回字面量 "
		System.out.println("\'");			// 返回字面量 '
		System.out.println("\\");			// 返回字面量 \
		System.out.println("\r");			// 回车
		System.out.println("\n");			// 换行
		System.out.println("\t");			// tab
	}
	
	/**
	 * 测试字符串常用 api
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testStringAIP () throws UnsupportedEncodingException {
		String str = "helloworld";
		String str2 = "我爱java";
		String str3 = "             helloworld";
		String str4 = "我爱java123我爱java123";
		
		System.out.println(str.length());
		System.out.println(str2.length());
		
		System.out.println(str.charAt(0));
		System.out.println(str.getBytes().length);
		System.out.println(str2.getBytes().length);
		System.out.println(str2.getBytes("UTF-8").length);
		
		
		System.out.println(str.toCharArray().length);
		System.out.println(str.equals("helloworld"));
		System.out.println(str.equalsIgnoreCase("helloWORLD"));
		System.out.println(str.contains("helloworld"));
		System.out.println(str.startsWith("hello"));
		System.out.println(str.endsWith("world"));
		System.out.println(str.split("o").length);
		
		
		System.out.println(str.replace("o", "O"));				// 替换全部,匹配字符串进行替换
		System.out.println(str.replaceFirst("o", "O"));			// 替换第一个,匹配正则进行替换
		System.out.println(str.replaceAll("o", "O"));			// 替换全部,匹配正则进行替换
		
		System.out.println(str4.replace("\\d", ""));			
		System.out.println(str4.replaceAll("\\d", ""));			// 替换成功
		
		
		System.out.println(str3);
		System.out.println(str3.trim());
	}
	
	/**
	 * 测试字符串格式化
	 */
	@Test
	public void testStringFormat () {
		String name = "tom";
		int age = 20;
		int height = 175;
		System.out.println(name + " 的年龄是 " + age + ",身高是 " + height + "cm");
		System.out.println(String.format("%s 的年龄是 %d,身高是 %dcm", name, age, height));
		
		System.out.println(String.format("%08d", 1001));
		System.out.println(String.format("%016d", 1001));
	}
}
