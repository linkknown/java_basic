package com.linkknown.basic;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

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
	
	
	/**
	 * ���Բ�ͬ�������ַ��ĳ���
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testBinary4 () throws UnsupportedEncodingException {
		System.out.println(Arrays.toString("a".getBytes("gbk")));
		System.out.println(Arrays.toString("a".getBytes("utf-8")));
		
		System.out.println(Arrays.toString("��".getBytes("gbk")));
		System.out.println(Arrays.toString("��".getBytes("gb2312")));
		System.out.println(Arrays.toString("��".getBytes("utf-8")));
		
		byte[] bytes1 = "��".getBytes("gbk");
		for (int i=0; i<bytes1.length; i++) {
			System.out.println(Integer.toBinaryString(bytes1[i] & 0xff));
		}
		
		System.out.println();
	
		/*
		 * ��� & 0xff ������
		 * &��ʾ��λ��,ֻ������λͬʱΪ1,���ܵõ�1
		 * 0x����16������,0xff��ʾ����������1111 1111 ռһ���ֽ�.�������&��������,���8λ,���ᷢ���仯.
		 */
		byte[] bytes2 = "��".getBytes("utf-8");
		for (int i=0; i<bytes2.length; i++) {
//			11111111111111111111111111100101	& 0xff = 00000000000000000000000011100101	=> 11100101
//			11111111111111111111111110011011	& 0xff = 00000000000000000000000010011011	=> 10011011
//			11111111111111111111111110111101	& 0xff = 00000000000000000000000010111101	=> 10111101
			System.out.println(Integer.toBinaryString(bytes2[i] & 0xff));		// & 0xff ����ȡ�Ͱ�λ,��ӡ���� byte ���͵Ķ�����
		}
		

		byte[] bytes3 = "��".getBytes("utf-8");
		for (int i=0; i<bytes3.length; i++) {
			// ����ת����ʽ�Ǵ����
			// ��ӡ���� int �������ݵĶ����ƣ������� byte ���͵Ķ�����
//			11111111111111111111111111100101
//			11111111111111111111111110011011
//			11111111111111111111111110111101
			System.out.println(Integer.toBinaryString((bytes3[i])));			
		}
	}
}
