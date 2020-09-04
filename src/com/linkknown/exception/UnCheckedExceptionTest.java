package com.linkknown.exception;

import org.junit.jupiter.api.Test;

/**
 * �Ǽ���쳣����
 * @author Administrator
 *
 */
public class UnCheckedExceptionTest {

	private static String toUpper(String str) {
		return str.toUpperCase();
	}
	
	/**
	 * ���Կ�ָ���쳣
	 */
	@Test
	public void testNullPointerException () {
		System.out.println(toUpper(null));
	}
	
	/**
	 * ���������±�Խ���쳣
	 */
	@Test
	public void testArrayIndexOutOfBoundsException () {
		int[] intArr = new int[2];
		intArr[2] = 10;
	}
	
	/**
	 * ���������쳣
	 */
	@Test
	public void testArithmeticException () {
		int i = 10 / 0;
	}
	
}

