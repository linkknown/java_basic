package com.linkknown.basic;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

	/**
	 * 算数运算符
	 */
	@Test
	public void testCal1() {
		System.out.println(1 + 2 - 3 * 4 / 5);

		System.out.println(9 % 5);

		int i = 10;
		i++;
		System.out.println(i);
		i--;
		System.out.println(i);

		int j = 10;
		++j;
		System.out.println(j);
		--j;
		System.out.println(j);
		
		System.out.println();

		int a = 10;
		System.out.println(a++);
		System.out.println(++a);
		System.out.println(a--);
		System.out.println(--a);
	}
	
	/**
	 * 测试关系运算符
	 */
	@Test
	public void testCal2 () {
		System.out.println(10 == 10);
		System.out.println(10 != 10);
		System.out.println(10 > 10);
		System.out.println(10 >= 10);
		System.out.println(10 < 10);
		System.out.println(10 <= 10);
	}
	
	/**
	 * 逻辑运算符
	 */
	@Test
	public void testCal3 () {
		System.out.println(10 > 10 && 10 <= 10);
		System.out.println(10 > 10 || 10 <= 10);	// 短路问题
		System.out.println(10 >= 10 || 10 <= 10);	// 短路问题
		System.out.println(!(10 >= 10));
	}
	
	/**
	 * 算法优先级,使用 （）提升优先级
	 */
	@Test
	public void testCal4 () {
		System.out.println((1 + 2) * 3);
	}
	
	/**
	 * 三目运算符
	 */
	@Test
	public void testCal5 () {
		boolean flag;
		// 产生一个随机数，判断是否大于 10
		int num = new Random().nextInt(20);
		if (num > 10) {
			flag = true;
		} else {
			flag = false;
		} 
		System.out.println(num);
		System.out.println(flag);
		
		// 三目运算符的作用是简化 if ... else 判断
		// 等价于
		flag = num > 10 ? true : false;
		System.out.println(flag);
		System.out.println(num > 10 ? true : false);
	}
}
