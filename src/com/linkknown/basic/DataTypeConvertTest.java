package com.linkknown.basic;

import org.junit.jupiter.api.Test;

public class DataTypeConvertTest {

	@Test
	public void testConvert () {
		// �����Զ�����ת��
		byte a = 100;
		int a1 = a;
		
		// �Զ�����ת����char �� int ��ת
		int b = 'a';
		System.out.println(b);
		char c = 97;
		System.out.println(c);
		
		
		// ����ǿ������ת��
		byte d = (byte)128;
		
		
		// ��������ǿ������ת��
		byte e = 3;
	}
}
