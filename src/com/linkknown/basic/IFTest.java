package com.linkknown.basic;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class IFTest {

	@Test
	public void testIf() {
		int number = new Random().nextInt(10);
		if (number > 0) {
			System.out.println("number ������!");
		}
	}

	@Test
	public void testIf2() {
		int number = new Random().nextInt(10);
		if (number > 0) {
			System.out.println("number ������!");
		} else {
			System.out.println("number �Ƿ�����!");
		}
	}

	@Test
	public void testIf3() {
		int number = new Random().nextInt(10);
		if (number > 0) {
			System.out.println("number ������!");
		} else if (number == 0) {
			System.out.println("number �� 0!");
		} else {
			System.out.println("number �Ǹ���!");
		}
	}

	@Test
	public void testIf4() {
		int number = new Random().nextInt(10);
		if (number > 0) {
			System.out.println("number ������!");
		} else {
			if (number == 0) {
				System.out.println("number �� 0!");
			} else {
				System.out.println("number �Ǹ���!");
			}
		}
	}
}
