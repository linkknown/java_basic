package com.linkknown.exception;

import org.junit.jupiter.api.Test;

/**
 * 非检查异常测试
 * @author Administrator
 *
 */
public class UnCheckedExceptionTest {

	private static String toUpper(String str) {
		return str.toUpperCase();
	}
	
	/**
	 * 测试空指针异常
	 */
	@Test
	public void testNullPointerException () {
		System.out.println(toUpper(null));
	}
	
	/**
	 * 测试数组下标越界异常
	 */
	@Test
	public void testArrayIndexOutOfBoundsException () {
		int[] intArr = new int[2];
		intArr[2] = 10;
	}
	
	/**
	 * 测试算数异常
	 */
	@Test
	public void testArithmeticException () {
		int i = 10 / 0;
	}
	
}

