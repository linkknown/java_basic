package com.linkknown.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * 字符流测试
 * @author Administrator
 *
 */
public class ReaderWriterTest {
	
	/**
	 * 字符流（写入字符串）
	 * @throws IOException
	 */
	@Test
	public void testWriter () throws IOException {
		Writer writer = new FileWriter("D:/io4.txt");
		writer.write("\t我爱编程，编程爱我！\n我爱编程，编程爱我！\n\t我爱编程，编程爱我！\n");
		writer.close();
	}
	
	/**
	 * 字符流,一次读取一个字符数组
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
	 * 使用字符流进行复制
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
