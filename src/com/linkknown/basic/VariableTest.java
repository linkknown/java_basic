package com.linkknown.basic;

import org.junit.jupiter.api.Test;

/**
 * ��������
 * 
 * @author Administrator
 *
 */
public class VariableTest {

	@Test
	public void testVariable() {
		int a; // ������������

		// ���������ȸ�ֵ��ʹ��
		// System.out.println(a);

		a = 1;
		System.out.println(a);

		int b, c, d; // ������������
		int e = 1, f = 2, g = 3; // ������������������ֵ
		byte h = 10; // ����һ������������ֵ
		String str = "helloworld"; // ����һ���ַ�������������ֵ
		double pi = 3.14159; // ������һ��˫���ȸ����ͱ��� pi
		char x = 'x'; // ������һ���ַ��ͱ��� x ������ֵ
	}
}
