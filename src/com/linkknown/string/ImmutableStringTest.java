package com.linkknown.string;

import org.junit.jupiter.api.Test;

/**
 * ���ɱ��ַ���������
 * @author Administrator
 *
 */
public class ImmutableStringTest {

	
	@Test
	public void testImmutableString () {
		String str = "helloworld";
		System.out.println(str);
		String upperStr = str.toUpperCase();
	
		System.out.println(upperStr);
		System.out.println(str);		// ����ǰ�� str ֵ����,֤�� toUpperCase �Ƿ������ַ���
	}
	
	@Test
	public void testImmutableString2 () {
		String str = "helloworld";
		System.out.println(str);
		String upperStr = str.substring(0, 5);
	
		System.out.println(upperStr);
		System.out.println(str);		// ����ǰ�� str ֵ����,֤�� substring �Ƿ������ַ���
	}
}
