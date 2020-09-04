package com.linkknown.basic;

import org.junit.jupiter.api.Test;

public class DataTypeTest {

	/**
	 * ����������ϰ
	 */
	@Test
	public void testDataType() {
		// byte\short\int\long\float\double\boolean\char

		System.out.println("Byte ռ���ֽ�����" + Byte.SIZE);
		System.out.println(Byte.MIN_VALUE);
		System.out.println(Byte.MAX_VALUE);

		System.out.println("Short ռ���ֽ�����" + Short.SIZE);
		System.out.println(Short.MIN_VALUE);
		System.out.println(Short.MAX_VALUE);

		System.out.println("Integer ռ���ֽ�����" + Integer.SIZE);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);

		System.out.println("Long ռ���ֽ�����" + Long.SIZE);
		System.out.println(Long.MIN_VALUE);
		System.out.println(Long.MAX_VALUE);

		System.out.println("Float ռ���ֽ�����" + Float.SIZE);
		System.out.println(Float.MIN_VALUE);
		System.out.println(Float.MAX_VALUE);

		System.out.println("Double ռ���ֽ�����" + Double.SIZE);
		System.out.println(Double.MIN_VALUE);
		System.out.println(Double.MAX_VALUE);

		System.out.println("Character ռ���ֽ�����" + Character.SIZE);
		System.out.println((int) Character.MIN_VALUE);
		System.out.println((int) Character.MAX_VALUE);

		System.out.println(Boolean.TRUE);
	}

	@Test
	public void testBasicType() {
		byte bt = 100;
		// byte bt2 = 200; // ����ô��
		short st = 30000;
		// short st2 = 65535; // ����ô��
		int it = 80000000;
		// int it2 = 21474836471; // ����ô��
		long lg = 9223372036854775807l;
		// long lg2 = 9223372036854775807343l; // ����ô��

		int it3 = Integer.MAX_VALUE + 1; // ���ԭ����ʲô��
		System.out.println(it3); // -2147483648
		System.out.println(Integer.MIN_VALUE); // -2147483648
	}
	
	@Test
	public void testBasicType2 () {
		// Byte.MAX_VALUE + 1 �Զ�ת���� Short�����Բ����
		System.out.println(Byte.MAX_VALUE + 1 == Byte.MIN_VALUE);		// ??  	false
		// Short.MAX_VALUE + 1 �Զ�ת���� Integer�����Բ����
		System.out.println(Short.MAX_VALUE + 1 == Short.MIN_VALUE);		// ??  	false
		
		// ����ԭ����
		System.out.println(Integer.MAX_VALUE + 1 == Integer.MIN_VALUE); // ??	true
		System.out.println(Long.MAX_VALUE + 1 == Long.MIN_VALUE);		// ??	true
	}
	
	@Test
	public void testBasicType3 () {
		// byte\short ������ͻ�����
		// int\long �򲻻�
//		byte bt = Byte.MAX_VALUE + 1;
//		short st = Short.MAX_VALUE + 1;
		
		int it = Integer.MAX_VALUE + 1;
		long lt = Long.MAX_VALUE + 1;
		
		byte bt2 = (byte) (Byte.MAX_VALUE + 1);
		short st2 = (short) (Short.MAX_VALUE + 1);
		
		short bt3 = Byte.MAX_VALUE + 1;
		int st3 = Short.MAX_VALUE + 1;
	}
	
	@Test
	public void testBasicType4 () {
		boolean flag = true;
		boolean flag2 = Boolean.FALSE;
		
		long num1 = 10L;
		float num2 = 10.0f;
		double num3 = 10.0d;
		double num4 = 10.0;
		
		int num5 = 30 * 356 * 24 * 3600 * 1000;			// 30 �����������ȷ��
		System.out.println(num5);
		long num6 = 30 * 356 * 24 * 3600 * 1000;		// 30 �����������ȷ��
		System.out.println(num6);
		long num7 = 30 * 356 * 24 * 3600 * 1000L;		// 30 �����������ȷ��
		System.out.println(num7);
	}
}
