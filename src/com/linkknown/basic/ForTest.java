package com.linkknown.basic;

import org.junit.jupiter.api.Test;

public class ForTest {

	/**
	 * ���� for ѭ��
	 */
	@Test
	public void testFor1() {
		// ѭ������ i,�� 0 ��ʼ����
		for (int i = 0; i < 20; i++) {
			System.out.print("value of i: " + i);
			System.out.print("\n"); // �ȼ��� System.out.println();
		}

		// ѭ������ x���� > 0 ��ʼ����
		for (int x = 10; x < 20; x++) {
			System.out.print("value of x: " + x);
			System.out.print("\n");
		}
	}

	/**
	 * ���� while ѭ��
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
	 * ���� do...while (){ }
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
	 * ��ӡ �žų˷���
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
	 * �ж������ʹ�ӡ��100������������ ����Ҳ��������ָ����1����Ȼ���У�����1���������ⲻ����������������Ȼ��������2��3��5��7��11��13������
	 */
	@Test
	public void testFor() {
		for (int i = 2; i <= 100; i++) {
			boolean flag = true;
			for (int j = 2; j < i; j++) { // �ų� 1 �ͱ���
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println("�ҵ���һ��������" + i);
			}
		}
	}

	/**
	 * ������ǿ for ѭ��
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
	 * ���� break
	 */
	@Test
	public void testBreak() {
		int[] numbers = new int[] { 10, 20, 30, 40, 50, 60 };
		for (int number : numbers) {
			if (number == 30) {
				// number == 30 ʱ����ѭ��
				break;
			}
			System.out.println(number);
		}
	}

	/**
	 * ���� continue
	 */
	@Test
	public void testContinue() {
		int[] numbers = new int[] { 10, 20, 30, 40, 50, 60 };
		for (int number : numbers) {
			if (number == 30) {
				// number == 30 ʱ������һ��ѭ��
				continue;
			}
			System.out.println(number);
		}
	}
	
	/**
	 * for ѭ�������÷�
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
	 * for ѭ�������÷�
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
	 * for ѭ�������÷�
	 */
	@Test
	public void testSpecialFor3() {
		for (;;) {
			System.out.println("helloworld...");
		}
	}

	/**
	 * for ѭ�������÷�
	 */
	@Test
	public void testSpecialFor4() {
		for (int i = 0, j = 0; i < 100 && j < 100; i += 1, j += 2) {
			System.out.println("i = " + i + " j = " + j);
		}
	}
}
