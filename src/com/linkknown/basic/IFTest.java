package com.linkknown.basic;

import org.junit.jupiter.api.Test;

public class IFTest {

	@Test
	public void testIf() {
		int number = 10;
		if (number > 0) {
			System.out.println("number 是正数!");
		}
	}

	@Test
	public void testIf2() {
		int number = 10;
		if (number > 0) {
			System.out.println("number 是正数!");
		} else {
			System.out.println("number 是非正数!");
		}
	}

	@Test
	public void testIf3() {
		int number = 10;
		if (number > 0) {
			System.out.println("number 是正数!");
		} else if (number == 0) {
			System.out.println("number 是 0!");
		} else {
			System.out.println("number 是负数!");
		}
	}

	@Test
	public void testIf4() {
		int number = 10;
		if (number > 0) {
			System.out.println("number 是正数!");
		} else {
			if (number == 0) {
				System.out.println("number 是 0!");
			} else {
				System.out.println("number 是负数!");
			}
		}
	}
}
