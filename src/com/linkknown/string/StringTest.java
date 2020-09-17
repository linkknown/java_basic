package com.linkknown.string;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * �ַ��� api ������
 * @author Administrator
 *
 */
public class StringTest {
	
	/**
	 * �����ַ���ת��
	 */
	@Test
	public void testTransformString () {
		System.out.println("\"");			// ���������� "
		System.out.println("\'");			// ���������� '
		System.out.println("\\");			// ���������� \
		System.out.println("\r");			// �س�
		System.out.println("\n");			// ����
		System.out.println("\t");			// tab
	}
	
	/**
	 * �����ַ������� api
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testStringAIP () throws UnsupportedEncodingException {
		String str = "helloworld";
		String str2 = "�Ұ�java";
		String str3 = "             helloworld";
		String str4 = "�Ұ�java123�Ұ�java123";
		
		System.out.println(str.length());
		System.out.println(str2.length());
		
		System.out.println(str.charAt(0));
		System.out.println(str.getBytes().length);
		System.out.println(str2.getBytes().length);
		System.out.println(str2.getBytes("UTF-8").length);
		
		
		System.out.println(str.toCharArray().length);
		System.out.println(str.equals("helloworld"));
		System.out.println(str.equalsIgnoreCase("helloWORLD"));
		System.out.println(str.contains("helloworld"));
		System.out.println(str.startsWith("hello"));
		System.out.println(str.endsWith("world"));
		System.out.println(str.split("o").length);
		
		
		System.out.println(str.replace("o", "O"));				// �滻ȫ��,ƥ���ַ��������滻
		System.out.println(str.replaceFirst("o", "O"));			// �滻��һ��,ƥ����������滻
		System.out.println(str.replaceAll("o", "O"));			// �滻ȫ��,ƥ����������滻
		
		System.out.println(str4.replace("\\d", ""));			
		System.out.println(str4.replaceAll("\\d", ""));			// �滻�ɹ�
		
		
		System.out.println(str3);
		System.out.println(str3.trim());
	}
	
	/**
	 * �����ַ�����ʽ��
	 */
	@Test
	public void testStringFormat () {
		String name = "tom";
		int age = 20;
		int height = 175;
		System.out.println(name + " �������� " + age + ",����� " + height + "cm");
		System.out.println(String.format("%s �������� %d,����� %dcm", name, age, height));
		
		System.out.println(String.format("%08d", 1001));
		System.out.println(String.format("%016d", 1001));
	}
}
