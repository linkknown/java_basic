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
	 * 验证字符串的不可变性
	 */
	@Test
	public void testImmutableString () {
		String str = "helloworld";
		System.out.println(str);
		String upperStr = str.toUpperCase();
	
		System.out.println(upperStr);
		System.out.println(str);		// 调用前后 str 值不变,证明 toUpperCase 是返回新字符串
	}
	
	@Test
	public void testImmutableString2 () {
		String str = "helloworld";
		System.out.println(str);
		String subStr = str.substring(0, 5);
	
		System.out.println(subStr);
		System.out.println(str);		// 调用前后 str 值不变,证明 substring 是返回新字符串
	}
	
	
	@Test
	public void testImmutableString3 () {
		String str = "helloworld";
		String str2 = "helloworld";
		String subStr = str.substring(0, 5);
	
		System.out.println(subStr);
		System.out.println(str2);	
	}
	
	
	
	
	/**
	 * 字符串连接测试
	 * @author Administrator
	 * 1.无论如何直接用“+”号连接字符串都是最慢的
	 * 2.在拼接少数字符串（不超过4个）的时候，concat效率是最高的
	 * 3.多个字符串拼接的时候，StringBuilder/StringBuffer的效率是碾压的
	 * 4.在不需要考虑线程安全问题的时候，使用StringBuilder的效率比StringBuffer更高
	 *
	 */
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
	
	
	
	
	/**
	 * 测试字符串转义
	 */
	@Test
	public void testTransformString () {
		System.out.println("\"");			// 返回字面量 "
		System.out.println("\'");			// 返回字面量 '
		System.out.println("\\");			// 返回字面量 \
		System.out.println("1~~~~\r~~~~1");	// \r 表示回车
		System.out.println("2~~~~\n~~~~2");	// \n 表示换行
		System.out.println("3~~~~\t~~~~3");	// \t 表示一个制表符
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
		
		System.out.println(str4.replace("\\d", ""));			// 替换不成功
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
