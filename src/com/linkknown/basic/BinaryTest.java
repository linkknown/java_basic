package com.linkknown.basic;

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
}
