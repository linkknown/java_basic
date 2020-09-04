package com.linkknown.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.Test;

/**
 * 字节流测试
 * @author Administrator
 *
 */
public class InputOutputStreamTest {

	/**
	 * 测试字节流写入
	 * @throws IOException
	 */
	@Test
	public void testOutputStream () throws IOException {
		OutputStream outputStream = new FileOutputStream("D:/io.txt");
		// 写一个字节
		// 65～90为26个大写英文字母，97～122号为26个小写英文字母，其余为一些标点符号、运算符号等。
		for (int i=65; i<=90; i++) {
			outputStream.write(i);
		}
		// 写一个字节数组
		String str = "我爱编程";
		byte[] bytes = str.getBytes("UTF-8");
		outputStream.write(bytes);
		outputStream.close();
	}
	
	/**
	 * 测试字符流读取
	 * @throws IOException
	 */
	@Test
	public void testInputStream () throws IOException {
		File file = new File("D:/io.txt");
		byte[] bytes = new byte[(int)file.length()];
		InputStream inputStream = new FileInputStream(file);
		int data;
		int index = 0;
		// 读取到 -1 代表读完了
		while ((data = inputStream.read()) != -1) {		// 一个字节一个字节读取
			System.out.println(data);
			bytes[index++] = (byte) data;
		}
		System.out.println(new String(bytes, "UTF-8"));
		inputStream.close();
	}
	
	/**
	 * 测试字符流读取（文件复制）
	 * 一次读取一个字节数组
	 * @throws IOException
	 */
	@Test
	public void testInputStream2 () throws IOException {
		InputStream inputStream = new FileInputStream("D:/io.txt");
		OutputStream outputStream = new FileOutputStream("D:/io2.txt");
		byte[] bytes = new byte[1000];
		int len = 0;
		while ((len = inputStream.read(bytes)) != -1) {		// len 表示实际读取的数量 -1 代表没有更多数据
			outputStream.write(bytes, 0, len);
		}
		inputStream.close();
	}
	
	
	/**
	 * 追加模式
	 * public FileOutputStream(String name, boolean append) append 为 true 时表示追加
	 * @throws IOException 
	 */
	@Test
	public void testAppend () throws IOException {
		OutputStream outputStream = new FileOutputStream("D:/io3.txt", true);		
//		OutputStream outputStream = new FileOutputStream("D:/io3.txt", false);
		outputStream.write("我爱编程".getBytes());
		outputStream.close();
	}
}
