package com.linkknown.basic;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * 二进制测试
 *
 */
public class BinaryTest {

	/**
	 * 练习打印 0 ~ 127 数字对应的二进制数字和其对应的字符
	 */
	@Test
	public void testBinary() {
		for (int i = 0; i <= 127; i++) {
			// System.out.println(Integer.toBinaryString(i));

			// 0 表示左补 0, 8 表示数字的长度
			// System.out.println(String.format("%08d",
			// Integer.parseInt(Integer.toBinaryString(i))));

			System.out.println(i + " 对应的二进制数字为 " + String.format("%08d", Integer.parseInt(Integer.toBinaryString(i)))
					+ ",对应的字符为 " + (char) i);
		}
	}

	/**
	 * 打印所有汉字对应的二进制数字和其对应的字符
	 */
	@Test
	public void testBinary3() {
		// 16 进制 \u4e00 - \u9fa5
		for (int i = 19968; i < 40869; i++) {
			System.out.println(i + " 对应的二进制数字为 " + String.format("%016d", Long.parseLong(Integer.toBinaryString(i)))
			+ ",对应的字符为 " + (char) i);
		}
	}

	// 结论：0~127 （8个bit，一个字节）只能表示键盘上的字符，表示中文至少要 （16 个 bit，2个字节）
	
	
	/**
	 * 测试不同编码下字符的长度
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testBinary4 () throws UnsupportedEncodingException {
		System.out.println(Arrays.toString("a".getBytes("gbk")));
		System.out.println(Arrays.toString("a".getBytes("utf-8")));
		
		System.out.println(Arrays.toString("国".getBytes("gbk")));
		System.out.println(Arrays.toString("国".getBytes("gb2312")));
		System.out.println(Arrays.toString("国".getBytes("utf-8")));
		
		byte[] bytes1 = "国".getBytes("gbk");
		for (int i=0; i<bytes1.length; i++) {
			System.out.print(Integer.toBinaryString(bytes1[i] & 0xff));
		}
		
		System.out.println();
	
		/*
		 * 详解 & 0xff 的作用
		 * & 表示按位与,只有两个位同时为1,才能得到1
		 * 0x代表16进制数,0xff表示的数二进制1111 1111 占一个字节.和其进行与 & 操作的数,最低8位,不会发生变化.
		 */
		byte[] bytes2 = "国".getBytes("utf-8");
		for (int i=0; i<bytes2.length; i++) {
//			11111111111111111111111111100101	& 0xff = 00000000000000000000000011100101	=> 11100101
//			11111111111111111111111110011011	& 0xff = 00000000000000000000000010011011	=> 10011011
//			11111111111111111111111110111101	& 0xff = 00000000000000000000000010111101	=> 10111101
			System.out.print(Integer.toBinaryString(bytes2[i] & 0xff));		// & 0xff 就是取低八位,打印的是 byte 类型的二进制
		}
		
		System.out.println();
		

		byte[] bytes3 = "国".getBytes("utf-8");
		for (int i=0; i<bytes3.length; i++) {
			// 这种转换方式是错误的
			// 打印的是 int 类型数据的二进制，而不是 byte 类型的二进制
//			11111111111111111111111111100101
//			11111111111111111111111110011011
//			11111111111111111111111110111101
			System.out.println(Integer.toBinaryString((bytes3[i])));			
		}
	}
	
	
	/**
	 * 1、原码：符号加上数字的二进制表示 +7：00000111  -7：100000111
	 * 2、反码：如果一个数为正，他的反码与原码相同，如果是负数那么符号位不变 其余位数取反   -7反码：111111000
	 * 3、补码：一个数如果为正其原码反码补码相同        如果是负数那么在反码加一
	 */
	@Test
	public void testBinary5() {
		// 打印 byte = 5 和 byte = -5  的二进制
		/*
		 * 结论1、在 二进制里,是用 0 和 1 来表示正负的,最高位为符号位,最高位为 1 代表负数,最高位为 0 代表正数
		 * 结论2、负数的二进制计算过程
		 *  以负数-5为例：
		 *  1.先将-5的绝对值转换成二进制,即为0000 0101;
		 *  2.然后求该二进制的反码,即为 1111 1010;
		 *  3.最后将反码加1,即为:1111 1011
		 */
		System.out.println(String.format("%08d", Integer.parseInt(Integer.toBinaryString(5))));		// 00000101
		System.out.println(Integer.toBinaryString(-5 & 0xff));										// 11111011
		
		// 打印 short = 5 和 short = -5 的二进制
		System.out.println(String.format("%016d", new BigInteger(Integer.toBinaryString(5))));		// 0000000000000101	
		System.out.println(Integer.toBinaryString(-5 & 0xffff));									// 1111111111111011
		
		// 打印 int = 5 和 int = -5 的二进制
		// 00000000000000000000000000000101
		System.out.println(String.format("%032d", new BigInteger(Integer.toBinaryString(5))));			
		// 11111111111111111111111111111011
		System.out.println(String.format("%032d", new BigInteger(Integer.toBinaryString(-5))));		
		// 11111111111111111111111111111011
		System.out.println(Integer.toBinaryString(-5 & 0xffffffff));
	
		// 打印 long = 5L 和 long = -5L 的二进制
		// 0000000000000000000000000000000000000000000000000000000000000101
		System.out.println(String.format("%064d", new BigInteger(Long.toBinaryString(5L))));			
		// 1111111111111111111111111111111111111111111111111111111111111011
		System.out.println(String.format("%064d", new BigInteger(Long.toBinaryString(-5L))));	
	}
}
