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
	 * ��֤�ַ����Ĳ��ɱ���
	 */
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
		String subStr = str.substring(0, 5);
	
		System.out.println(subStr);
		System.out.println(str);		// ����ǰ�� str ֵ����,֤�� substring �Ƿ������ַ���
	}
	
	
	@Test
	public void testImmutableString3 () {
		String str = "helloworld";
		String str2 = "helloworld";
		String subStr = str.substring(0, 5);
	
		System.out.println(subStr);
		System.out.println(str2);	
	}
	
	
	
	
	/**
	 * �ַ������Ӳ���
	 * @author Administrator
	 * 1.�������ֱ���á�+���������ַ�������������
	 * 2.��ƴ�������ַ�����������4������ʱ��concatЧ������ߵ�
	 * 3.����ַ���ƴ�ӵ�ʱ��StringBuilder/StringBuffer��Ч������ѹ��
	 * 4.�ڲ���Ҫ�����̰߳�ȫ�����ʱ��ʹ��StringBuilder��Ч�ʱ�StringBuffer����
	 *
	 */
	/**
	 * + ������
	 */
	@Test
	public void testAdd () {
		System.out.println("hello" + "world");
		System.out.println("hello" + 'a');
		System.out.println("hello" + 12);
	}
	
	/**
	 * concat ����
	 */
	@Test
	public void testConcat () {
		System.out.println("hello".concat("world"));
	}
	
	/**
	 * StringBuilder ��,�̲߳���ȫ��Ч�ʸ��� StringBuffer
	 */
	@Test
	public void testStringBuilder () {
		StringBuilder sb = new StringBuilder();
		sb.append("hello");
		sb.append("world");
		sb.append("hello");
		sb.append("world...");
		System.out.println(sb.toString());
	}
	
	/**
	 * StringBuffer ��,�̰߳�ȫ
	 */
	@Test
	public void testStringBuffer () {
		StringBuffer sb = new StringBuffer();
		sb.append("hello");
		sb.append("world");
		sb.append("hello");
		sb.append("world...");
		System.out.println(sb.toString());
	}
	
	
	/**
	 * �������ӷ�ʽ���ܱȽ�
	 * 1.�������ֱ���á�+���������ַ������������� 
	 * 2.��ƴ�������ַ�����������4������ʱ��concatЧ������ߵ�
	 * 3.����ַ���ƴ�ӵ�ʱ��StringBuilder/StringBuffer��Ч������ѹ��
	 * 4.�ڲ���Ҫ�����̰߳�ȫ�����ʱ��ʹ��StringBuilder��Ч�ʱ�StringBuffer����
	 */
	@Test
	public void testStringConcat () {
		
		// +
		String initialStr = "";
		long start = System.currentTimeMillis();
		for (int i=0; i<100000; i++) {
			initialStr = initialStr + "0";
		}
		long end = System.currentTimeMillis();
		System.out.println(String.format("+ total cost time %d (ms)", end - start));
		
		
		// concat
		initialStr = "";
		start = System.currentTimeMillis();
		for (int i=0; i<100000; i++) {
			initialStr = initialStr.concat("0");
		}
		end = System.currentTimeMillis();
		System.out.println(String.format("concat total cost time %d (ms)", end - start));
		
		
		// StringBuilder
		StringBuilder sb = new StringBuilder();
		start = System.currentTimeMillis();
		for (int i=0; i<100000; i++) {
			sb = sb.append("0");
		}
		end = System.currentTimeMillis();
		System.out.println(String.format("StringBuilder total cost time %d (ms)", end - start));
	
		
		// StringBuffer
		StringBuffer sbf = new StringBuffer();
		start = System.currentTimeMillis();
		for (int i=0; i<100000; i++) {
			sbf = sbf.append("0");
		}
		end = System.currentTimeMillis();
		System.out.println(String.format("StringBuffer total cost time %d (ms)", end - start));
	}
	
	
	
	
	/**
	 * �����ַ���ת��
	 */
	@Test
	public void testTransformString () {
		System.out.println("\"");			// ���������� "
		System.out.println("\'");			// ���������� '
		System.out.println("\\");			// ���������� \
		System.out.println("1~~~~\r~~~~1");	// \r ��ʾ�س�
		System.out.println("2~~~~\n~~~~2");	// \n ��ʾ����
		System.out.println("3~~~~\t~~~~3");	// \t ��ʾһ���Ʊ��
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
		
		System.out.println(str4.replace("\\d", ""));			// �滻���ɹ�
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
