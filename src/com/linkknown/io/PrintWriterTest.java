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
	
	
	@Test
	public void testBufferedWriter () throws IOException, InterruptedException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/io.txt")));
	
		for (int i=0; i<100; i++) {
			writer.write("helloworld\n");
		
			// BufferedReader ��Ҫ�ֶ� flush ����
//			writer.flush();
			
//			֧�ֲ�ͬƽ̨�Ļ��з���Ҫ��ʾ�ص��� newLine ����
//			writer.newLine();
			
			TimeUnit.SECONDS.sleep(1);
		}
		
		//   flushBuffer(); ǿ��������ļ�
		writer.close();
	}

	@Test
	public void testPrintWriter () throws FileNotFoundException, InterruptedException {
		// PrintWriter ��ʹ�� BufferedWriter ��װ��,�� BufferedWriter ���ܸ�ǿ��
		PrintWriter writer = new PrintWriter(new FileOutputStream("D:/io.txt"));
		
		for (int i=0; i<100; i++) {
			writer.write("helloworld\n");
			
			// PrintWriter Ҳ֧���ֶ� flush
			writer.flush();
		
			TimeUnit.SECONDS.sleep(1);
		}
		
		writer.close();
	}
	
	/**
	 * PrintWriter ��BufferedWriter���߼�,���к���OutputStream��File��Writer�Ĺ��췽��
	 * BufferedWriterֻ����writer�� ����PrintWriter���и�ʽ���������println������
	 * ���������ƽ̨�����ܵĻ��з�,��Ҳ��Ҳ��Ϊʲô��PrintWriterдHTML��ԭ��.
	 * 
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	@Test
	public void testPrintWriter2 () throws FileNotFoundException, InterruptedException {
		// PrintWriter ��ʹ�� BufferedWriter ��װ��,�� BufferedWriter ���ܸ�ǿ��
		// PrintWriter ֧���Զ���ˢ��
		PrintWriter writer = new PrintWriter(new FileOutputStream("D:/io.txt"), true);
		
		for (int i=0; i<100; i++) {
			// ���߿��Ƶȼ�
//			writer.write("helloworld\n");
			
			// PrintWriter �� println ����֧�ֵĻ��з�����,�� html ��ӡ <br/>
			writer.println("helloworld");
			
			TimeUnit.SECONDS.sleep(1);
		}
		
		writer.close();
	}
}
