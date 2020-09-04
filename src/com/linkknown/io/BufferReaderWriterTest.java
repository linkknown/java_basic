package com.linkknown.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.junit.jupiter.api.Test;

/**
 * 测试缓冲流
 * 
 * @author Administrator
 *
 */
public class BufferReaderWriterTest {

	/**
	 * 测试 BufferedReader\InputStreamReader
	 * 
	 * @throws IOException
	 */
	@Test
	public void testBufferedReader() throws IOException {
		InputStream inputStream = new FileInputStream("D:/io5.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();
		inputStream.close();
	}

	/**
	 * 测试 BufferedReader\OutputStreamWriter
	 * @throws IOException 
	 */
	@Test
	public void testBufferedWriter() throws IOException {
		OutputStream outputStream = new FileOutputStream("D:/io6.txt");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
		for (int i=0; i<100; i++) {
			writer.write("Java 是由Sun Microsystems公司于1995年5月推出的高级程序设计语言。 ~~ " + i + "\n");
		}
		// 关闭处理流会自动关闭节点流
		writer.close();
	}
}
