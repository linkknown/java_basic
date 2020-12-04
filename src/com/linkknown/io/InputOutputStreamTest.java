package com.linkknown.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * �ֽ�������
 * @author Administrator
 *
 */
public class InputOutputStreamTest {

	/**
	 * �����ֽ���д�� 
	 * ���ۣ�д�ļ���ʱ����뱣֤�ļ��д���
	 */
	@Test
	public void testOutputStream () throws IOException {
		File file = new File("D:/java/linkknown/io.txt");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		OutputStream outputStream = new FileOutputStream(file);
		
		// дһ���ֽڣ�65��90Ϊ26����дӢ����ĸ��97��122��Ϊ26��СдӢ����ĸ������ΪһЩ�����š�������ŵȡ�
		for (int i=65; i<=90; i++) {
			outputStream.write(i);
		}
		
		// �� a ~ z д���ļ�
		for (int i = 'a'; i < 'z'; i++) {
			outputStream.write(i);
		}
		
		// �� 1 ���ֽڷ�Χ��-128~127��д���ļ�
		for (int i=-128; i<=127; i++) {
			outputStream.write(i);
		}
		
		// дһ���ֽ�����
		String str = "�Ұ����";
		outputStream.write(str.getBytes("UTF-8"));
		outputStream.write(str.getBytes("GBK"));
		outputStream.close();
	}
	
	/**
	 * �����ֽ�����ȡ
	 * һ���ֽ�һ���ֽڶ�ȡ
	 * @throws IOException
	 */
	@Test
	public void testInputStream () throws IOException {
		File file = new File("D:/java/linkknown/io.txt");
		InputStream inputStream = new FileInputStream(file);
		
		int byteData;
		/*
		 * InputStream.read()����һ�� unsigned byte[0 ~ 255]
		 * byte�ķ�Χ��[-128��127]���������read()���ص�����[128��255]�ķ�Χ��ʱ�����ʾ��������
		 * (byte)128=-128
		 * (byte)129=-127
		 * (byte)255=-1
		 */
		while ((byteData = inputStream.read()) != -1) {
			System.out.println(byteData);
		}
		
		System.out.println("һ�� " + file.length() + " �ֽ�");
		
		inputStream.close();
	}
	
	/**
	 * ��ȡһ���ֽ�����,һ�ζ�ȡ 10 ��
	 */
	@Test
	public void testInputStream2 () throws IOException {
		File file = new File("D:/java/linkknown/io.txt");
		InputStream inputStream = new FileInputStream(file);

		byte[] realBytes = new byte[(int) file.length()];
		
		int len = 0, i = 0;
		byte[] bytes = new byte[10];
		
		while ((len = inputStream.read(bytes)) != -1) {
			System.out.println("��ȡ��" + len + "���ֽ�" + Arrays.toString(bytes));
			
			System.arraycopy(bytes, 0, realBytes, i * 10, len);   // ʵ�ʶ�ȡ���� len �����ȣ���һ������ 10 ��
			
			i++;
		}
		
		System.out.println("һ�� " + file.length() + " �ֽ�");
		System.out.println(new String(realBytes, "GBK"));
		System.out.println(new String(realBytes, "UTF-8"));
		
		inputStream.close();
	}
	
	/**
	 * һ����ȫ����ȡ����
	 * @throws IOException
	 */
	@Test
	public void testInputStream3 () throws IOException {
		File file = new File("D:/java/linkknown/io.txt");
		InputStream inputStream = new FileInputStream(file);

		byte[] realBytes = new byte[(int) file.length()];
		
		while (inputStream.read(realBytes) != -1) {
			System.out.println("һ�� " + file.length() + " �ֽ�");
			System.out.println(new String(realBytes, "GBK"));
			System.out.println(new String(realBytes, "UTF-8"));
		}
		
		inputStream.close();
	}
	
	
	/**
	 * �����ַ�����ȡ���ļ����ƣ�
	 * һ�ζ�ȡһ���ֽ�����
	 * @throws IOException
	 */
	@Test
	public void testInputOutput () throws IOException {
		File file1 = new File("D:/java/linkknown/io1.txt");
		File file2 = new File("D:/java/linkknown/io2.txt");
		
		InputStream inputStream = new FileInputStream(file1);
		OutputStream outputStream = new FileOutputStream(file2);
		
		int len = 0;
		byte[] bytes = new byte[1000];
		while ((len = inputStream.read(bytes)) != -1) {		// len ��ʾʵ�ʶ�ȡ������ -1 ����û�и�������
			outputStream.write(bytes, 0, len);
		}
		inputStream.close();
		outputStream.close();
	}
	
	
	/**
	 * ׷��ģʽ
	 * public FileOutputStream(String name, boolean append) append Ϊ true ʱ��ʾ׷��
	 * @throws IOException 
	 */
	@Test
	public void testAppend () throws IOException {
		File file1 = new File("D:/java/linkknown/io1.txt");
		File file2 = new File("D:/java/linkknown/io2.txt");
		
		InputStream inputStream = new FileInputStream(file1);
		OutputStream outputStream = new FileOutputStream(file2, true);
		
		int len = 0;
		byte[] bytes = new byte[1000];
		while ((len = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, len);
		}
		
		inputStream.close();
		outputStream.close();
	}
}
