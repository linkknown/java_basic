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
 * ���Ի�����
 * 1.FileReader����һ���ж���BufferedReader����һ���еض�
 * 2.BufferedReader����һ���еض�Ч�ʸߣ���Ϊ������IO�Ĵ���
 * 
 * @author Administrator
 *
 */
public class BufferReaderWriterTest {

	/**
	 * ���� BufferedReader\InputStreamReader
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
	 * ���� BufferedReader\OutputStreamWriter
	 * @throws IOException 
	 */
	@Test
	public void testBufferedWriter() throws IOException {
		OutputStream outputStream = new FileOutputStream("D:/io6.txt");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
		for (int i=0; i<100; i++) {
			writer.write("Java ����Sun Microsystems��˾��1995��5���Ƴ��ĸ߼�����������ԡ� ~~ " + i + "\n");
		}
		// �رմ��������Զ��رսڵ���
		writer.close();
	}
}
