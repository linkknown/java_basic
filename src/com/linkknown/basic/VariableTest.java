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
		
		a = 20;
		System.out.println(a);		// ���������޸�ֵ
		
//		a = "helloworld";			// ��������ȷ���Ͳ������޸�
		
		testVariableScope();		// ����������
		
		testIdentifier();			// ���� Java ��ʶ��
	}
	
	public static void testVariableScope () {
		int a = 1000;				// ���� a ���ڱ���������Ч
	}
	
	
	public static void testIdentifier () {
		// ��ʶ����ֻ������ĸ\����\$\_
		// ��ʶ�����������ֿ�ͷ
		int a0$_ = 10;
		System.out.println(a0$_);
		int $0 = 10;
		int _ = 10;
//		int 3a = 10;
		
		// ��ʶ���ϸ����ִ�Сд
		int num = 10;
		String NUM = "20";
		
		// �����ùؼ���������һ������
//		int class = 10;
	}
}
