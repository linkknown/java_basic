package com.linkknown.basic;

import org.junit.jupiter.api.Test;

/**
 * 变量测试
 * 
 * @author Administrator
 *
 */
public class VariableTest {

	@Test
	public void testVariable() {
		int a; // 声明单个变量

		// 变量必须先赋值再使用
		// System.out.println(a);

		a = 1;
		System.out.println(a);

		int b, c, d; // 声明三个变量
		int e = 1, f = 2, g = 3; // 申明三个变量并赋初值
		byte h = 10; // 申明一个变量并赋初值
		String str = "helloworld"; // 申明一个字符串变量并赋初值
		double pi = 3.14159; // 申明了一个双精度浮点型变量 pi
		char x = 'x'; // 申明了一个字符型变量 x 并赋初值
	}
}
