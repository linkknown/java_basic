package com.linkknown.basic;

import java.io.UnsupportedEncodingException;
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
			System.out.println(Integer.toBinaryString(bytes1[i] & 0xff));
		}
		
		System.out.println();
	
		/*
		 * 详解 & 0xff 的作用
		 * &表示按位与,只有两个位同时为1,才能得到1
		 * 0x代表16进制数,0xff表示的数二进制1111 1111 占一个字节.和其进行&操作的数,最低8位,不会发生变化.
		 */
		byte[] bytes2 = "国".getBytes("utf-8");
		for (int i=0; i<bytes2.length; i++) {
//			11111111111111111111111111100101	& 0xff = 00000000000000000000000011100101	=> 11100101
//			11111111111111111111111110011011	& 0xff = 00000000000000000000000010011011	=> 10011011
//			11111111111111111111111110111101	& 0xff = 00000000000000000000000010111101	=> 10111101
			System.out.println(Integer.toBinaryString(bytes2[i] & 0xff));		// & 0xff 就是取低八位,打印的是 byte 类型的二进制
		}
		

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
}
