package com.linkknown.basic;

import org.junit.jupiter.api.Test;

public class ForTest {

	/**
	 * 测试 for 循环
	 */
	@Test
	public void testFor1() {
		// 循环变量 i,从 0 开始技术
		for (int i = 0; i < 20; i++) {
			System.out.print("value of i: " + i);
			System.out.print("\n"); // 等价于 System.out.println();
		}

		// 循环变量 x，从 > 0 开始计数
		for (int x = 10; x < 20; x++) {
			System.out.print("value of x: " + x);
			System.out.print("\n");
		}
	}

	/**
	 * 测试 while 循环
	 */
	@Test
	public void testWhile() {
		int x = 10;
		while (x < 20) {
			System.out.println("value of x is:" + x);
			x++;
		}
	}

	/**
	 * 测试 do...while (){ }
	 */
	@Test
	public void testDoWhile() {
		int x = 10;
		do {
			System.out.println("value of x is:" + x);
			x++;
		} while (x < 20);
	}

	/**
	 * 打印 九九乘法表
	 */
	@Test
	public void test9X9() {
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(String.format("%dx%d=%d\t", i, j, i * j));
			}
			System.out.println();
		}
	}

	/**
	 * 判断素数和打印出100以内所有素数 素数也叫质数，指大于1的自然数中，除了1和它本身外不再有其他因数的自然数，比如2、3、5、7、11、13……。
	 */
	@Test
	public void testFor() {
		for (int i = 2; i <= 100; i++) {
			boolean flag = true;
			for (int j = 2; j < i; j++) { // 排除 1 和本身
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println("找到了一个素数：" + i);
			}
		}
	}

	/**
	 * 测试增强 for 循环
	 */
	@Test
	public void testEnhanceFor() {
		int[] numbers = { 10, 20, 30, 40, 50, 60 };
		for (int number : numbers) {
			System.out.print(number);
			System.out.println(",");
		}

		String[] names = new String[] { "James", "Larray", "Tom", "Lacy" };
		for (String name : names) {
			System.out.print(name + ",");
		}
	}

	/**
	 * 测试 break
	 */
	@Test
	public void testBreak() {
		int[] numbers = new int[] { 10, 20, 30, 40, 50, 60 };
		for (int number : numbers) {
			if (number == 30) {
				// number == 30 时跳出循环
				break;
			}
			System.out.println(number);
		}
	}

	/**
	 * 测试 continue
	 */
	@Test
	public void testContinue() {
		int[] numbers = new int[] { 10, 20, 30, 40, 50, 60 };
		for (int number : numbers) {
			if (number == 30) {
				// number == 30 时跳到下一次循环
				continue;
			}
			System.out.println(number);
		}
	}
	
	/**
	 * for 循环特殊用法
	 */
	@Test
	public void testSpecialFor() {
		int sum = 0;
		int i = 0;
		for (; i < 20; i++) {
			sum += i;
		}
		System.out.println("sum = " + sum);
	}

	/**
	 * for 循环特殊用法
	 */
	@Test
	public void testSpecialFor2() {
		int sum = 0;
		for (int i = 0; i < 20;) {
			sum += i;
			i++;
		}
		System.out.println("sum = " + sum);
	}

	/**
	 * for 循环特殊用法
	 */
	@Test
	public void testSpecialFor3() {
		for (;;) {
			System.out.println("helloworld...");
		}
	}

	/**
	 * for 循环特殊用法
	 */
	@Test
	public void testSpecialFor4() {
		for (int i = 0, j = 0; i < 100 && j < 100; i += 1, j += 2) {
			System.out.println("i = " + i + " j = " + j);
		}
	}
}
