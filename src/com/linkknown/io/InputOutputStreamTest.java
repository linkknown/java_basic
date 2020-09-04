package com.linkknown.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.Test;

/**
 * �ֽ�������
 * @author Administrator
 *
 */
public class InputOutputStreamTest {

	/**
	 * �����ֽ���д��
	 * @throws IOException
	 */
	@Test
	public void testOutputStream () throws IOException {
		OutputStream outputStream = new FileOutputStream("D:/io.txt");
		// дһ���ֽ�
		// 65��90Ϊ26����дӢ����ĸ��97��122��Ϊ26��СдӢ����ĸ������ΪһЩ�����š�������ŵȡ�
		for (int i=65; i<=90; i++) {
			outputStream.write(i);
		}
		// дһ���ֽ�����
		String str = "�Ұ����";
		byte[] bytes = str.getBytes("UTF-8");
		outputStream.write(bytes);
		outputStream.close();
	}
	
	/**
	 * �����ַ�����ȡ
	 * @throws IOException
	 */
	@Test
	public void testInputStream () throws IOException {
		File file = new File("D:/io.txt");
		byte[] bytes = new byte[(int)file.length()];
		InputStream inputStream = new FileInputStream(file);
		int data;
		int index = 0;
		// ��ȡ�� -1 ���������
		while ((data = inputStream.read()) != -1) {		// һ���ֽ�һ���ֽڶ�ȡ
			System.out.println(data);
			bytes[index++] = (byte) data;
		}
		System.out.println(new String(bytes, "UTF-8"));
		inputStream.close();
	}
	
	/**
	 * �����ַ�����ȡ���ļ����ƣ�
	 * һ�ζ�ȡһ���ֽ�����
	 * @throws IOException
	 */
	@Test
	public void testInputStream2 () throws IOException {
		InputStream inputStream = new FileInputStream("D:/io.txt");
		OutputStream outputStream = new FileOutputStream("D:/io2.txt");
		byte[] bytes = new byte[1000];
		int len = 0;
		while ((len = inputStream.read(bytes)) != -1) {		// len ��ʾʵ�ʶ�ȡ������ -1 ����û�и�������
			outputStream.write(bytes, 0, len);
		}
		inputStream.close();
	}
	
	
	/**
	 * ׷��ģʽ
	 * public FileOutputStream(String name, boolean append) append Ϊ true ʱ��ʾ׷��
	 * @throws IOException 
	 */
	@Test
	public void testAppend () throws IOException {
		OutputStream outputStream = new FileOutputStream("D:/io3.txt", true);		
//		OutputStream outputStream = new FileOutputStream("D:/io3.txt", false);
		outputStream.write("�Ұ����".getBytes());
		outputStream.close();
	}
}
