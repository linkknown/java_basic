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
		
			// BufferedReader 需要手动 flush 才行
//			writer.flush();
			
//			支持不同平台的换行符需要显示地调用 newLine 方法
//			writer.newLine();
			
			TimeUnit.SECONDS.sleep(1);
		}
		
		//   flushBuffer(); 强制输出到文件
		writer.close();
	}

	@Test
	public void testPrintWriter () throws FileNotFoundException, InterruptedException {
		// PrintWriter 是使用 BufferedWriter 包装的,比 BufferedWriter 功能更强大
		PrintWriter writer = new PrintWriter(new FileOutputStream("D:/io.txt"));
		
		for (int i=0; i<100; i++) {
			writer.write("helloworld\n");
			
			// PrintWriter 也支持手动 flush
			writer.flush();
		
			TimeUnit.SECONDS.sleep(1);
		}
		
		writer.close();
	}
	
	/**
	 * PrintWriter 比BufferedWriter更高级,它有含有OutputStream、File、Writer的构造方法
	 * BufferedWriter只接受writer。 而且PrintWriter还有格式化输出方法println（），
	 * 能输出各个平台都接受的换行符,这也许也是为什么用PrintWriter写HTML的原因.
	 * 
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	@Test
	public void testPrintWriter2 () throws FileNotFoundException, InterruptedException {
		// PrintWriter 是使用 BufferedWriter 包装的,比 BufferedWriter 功能更强大
		// PrintWriter 支持自动行刷新
		PrintWriter writer = new PrintWriter(new FileOutputStream("D:/io.txt"), true);
		
		for (int i=0; i<100; i++) {
			// 两者看似等价
//			writer.write("helloworld\n");
			
			// PrintWriter 的 println 方法支持的换行符更多,如 html 打印 <br/>
			writer.println("helloworld");
			
			TimeUnit.SECONDS.sleep(1);
		}
		
		writer.close();
	}
}
