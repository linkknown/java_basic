package com.linkknown.basic;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

	/**
	 * ���������
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
	 * ���Թ�ϵ�����
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
	 * �߼������
	 */
	@Test
	public void testCal3 () {
		System.out.println(10 > 10 && 10 <= 10);
		System.out.println(10 > 10 || 10 <= 10);	// ��·����
		System.out.println(10 >= 10 || 10 <= 10);	// ��·����
		System.out.println(!(10 >= 10));
	}
	
	/**
	 * �㷨���ȼ�,ʹ�� �����������ȼ�
	 */
	@Test
	public void testCal4 () {
		System.out.println((1 + 2) * 3);
	}
	
	/**
	 * ��Ŀ�����
	 */
	@Test
	public void testCal5 () {
		boolean flag;
		// ����һ����������ж��Ƿ���� 10
		int num = new Random().nextInt(20);
		if (num > 10) {
			flag = true;
		} else {
			flag = false;
		} 
		System.out.println(num);
		System.out.println(flag);
		
		// ��Ŀ������������Ǽ� if ... else �ж�
		// �ȼ���
		flag = num > 10 ? true : false;
		System.out.println(flag);
		System.out.println(num > 10 ? true : false);
	}
}
