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
	 * Close 时候才会刷新到文件
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testBufferedWriter () throws IOException, InterruptedException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/io.txt")));
	
		for (int i=0; i<100; i++) {
			// 不会立即写入文件
			writer.write("helloworld\n");
			
			TimeUnit.SECONDS.sleep(1);
		}
		
		// 源码：public void close() => flushBuffer();
		writer.close();
	}
	
	
	/**
	 * 使用 flush() 将缓冲区的数据强制输出,用于清空缓冲区,若直接调用close()方法,则可能会丢失缓冲区的数据.所以通俗来讲它起到的是刷新的作用.
		
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
