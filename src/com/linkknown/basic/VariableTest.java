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
		
		a = 20;
		System.out.println(a);		// 变量可以修改值
		
//		a = "helloworld";			// 变量类型确定就不能再修改
		
		testVariableScope();		// 变量作用域
		
		testIdentifier();			// 测试 Java 标识符
	}
	
	public static void testVariableScope () {
		int a = 1000;				// 变量 a 仅在本方法里有效
	}
	
	
	public static void testIdentifier () {
		// 标识符：只有能字母\数字\$\_
		// 标识符不能以数字开头
		int a0$_ = 10;
		System.out.println(a0$_);
		int $0 = 10;
		int _ = 10;
//		int 3a = 10;
		
		// 标识符严格区分大小写
		int num = 10;
		String NUM = "20";
		
		// 不能用关键字来声明一个变量
//		int class = 10;
	}
}
