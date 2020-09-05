package com.linkknown.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * �ַ�������
 * @author Administrator
 *
 */
public class ReaderWriterTest {
	
	/**
	 * �ַ�����д���ַ�����
	 * @throws IOException
	 */
	@Test
	public void testWriter () throws IOException {
		Writer writer = new FileWriter("D:/io4.txt");
		writer.write("\t�Ұ���̣���̰��ң�\n�Ұ���̣���̰��ң�\n\t�Ұ���̣���̰��ң�\n");
		writer.close();
	}
	
	/**
	 * �ַ���,һ�ζ�ȡһ���ַ�����
	 * @throws IOException
	 */
	@Test
	public void testReader () throws IOException {
		FileReader reader = new FileReader("D:/io4.txt");
		 char[] chrs = new char[50];
		 int len=0;
		 while ((len = reader.read(chrs)) != -1) {
			 System.out.print(Arrays.copyOfRange(chrs, 0, len));
		 }
		reader.close();
	}
	
	/**
	 * ʹ���ַ������и���
	 * @throws IOException 
	 */
	@Test
	public void testCopy () throws IOException {
		Reader reader = new FileReader("D:/io4.txt");
		Writer writer = new FileWriter("D:/io5.txt");
		 char[] chrs = new char[50];
		 int len=0;
		 while ((len = reader.read(chrs)) != -1) {
			 writer.write(chrs, 0, len);
		 }
		 writer.close();
		 reader.close();
	}
}
