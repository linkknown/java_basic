package com.linkknown.basic;

import org.junit.jupiter.api.Test;

/**
 * �����Ʋ���
 *
 */
public class BinaryTest {

	/**
	 * ��ϰ��ӡ 0 ~ 127 ���ֶ�Ӧ�Ķ��������ֺ����Ӧ���ַ�
	 */
	@Test
	public void testBinary() {
		for (int i = 0; i <= 127; i++) {
			// System.out.println(Integer.toBinaryString(i));

			// 0 ��ʾ�� 0, 8 ��ʾ���ֵĳ���
			// System.out.println(String.format("%08d",
			// Integer.parseInt(Integer.toBinaryString(i))));

			System.out.println(i + " ��Ӧ�Ķ���������Ϊ " + String.format("%08d", Integer.parseInt(Integer.toBinaryString(i)))
					+ ",��Ӧ���ַ�Ϊ " + (char) i);
		}
	}

	/**
	 * ��ӡ���к��ֶ�Ӧ�Ķ��������ֺ����Ӧ���ַ�
	 */
	@Test
	public void testBinary3() {
		// 16 ���� \u4e00 - \u9fa5
		for (int i = 19968; i < 40869; i++) {
			System.out.println(i + " ��Ӧ�Ķ���������Ϊ " + String.format("%016d", Long.parseLong(Integer.toBinaryString(i)))
			+ ",��Ӧ���ַ�Ϊ " + (char) i);
		}
	}

	// ���ۣ�0~127 ��8��bit��һ���ֽڣ�ֻ�ܱ�ʾ�����ϵ��ַ�����ʾ��������Ҫ ��16 �� bit��2���ֽڣ�
}
