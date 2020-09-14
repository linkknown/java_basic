package com.linkknown.io;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class PrintWriterTest {

	/**
	 * Close ʱ��Ż�ˢ�µ��ļ�
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testBufferedWriter () throws IOException, InterruptedException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/io.txt")));
	
		for (int i=0; i<100; i++) {
			// ��������д���ļ�
			writer.write("helloworld\n");
			
			TimeUnit.SECONDS.sleep(1);
		}
		
		// Դ�룺public void close() => flushBuffer();
		writer.close();
	}
	
	
	/**
	 * ʹ�� flush() ��������������ǿ�����,������ջ�����,��ֱ�ӵ���close()����,����ܻᶪʧ������������.����ͨ���������𵽵���ˢ�µ�����.
		
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	@Test
	public void testPrintWriter () throws FileNotFoundException, InterruptedException {
		PrintWriter writer = new PrintWriter(new FileOutputStream("D:/io.txt"));
	
		for (int i=0; i<100; i++) {
//			writer.write("helloworld\n");
			writer.println("helloworld");
			
			writer.flush();
			
			TimeUnit.SECONDS.sleep(1);
		}
		
		writer.close();
	}
	
	@Test
	public void testPrintWriter3 () throws FileNotFoundException, InterruptedException {
		PrintWriter writer = new PrintWriter(new FileOutputStream("D:/io.txt"), true);
	
		for (int i=0; i<100; i++) {
			writer.write("helloworld\n");
			
			TimeUnit.SECONDS.sleep(1);
		}
		
		writer.close();
	}
}
