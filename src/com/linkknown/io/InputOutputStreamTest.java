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
 * 字节流测试
 * @author Administrator
 *
 */
public class InputOutputStreamTest {

	/**
	 * 测试字节流写入 
	 * 结论：写文件的时候必须保证文件夹存在
	 */
	@Test
	public void testOutputStream () throws IOException {
		File file = new File("D:/java/linkknown/io.txt");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		OutputStream outputStream = new FileOutputStream(file);
		
		// 写一个字节，65～90为26个大写英文字母，97～122号为26个小写英文字母，其余为一些标点符号、运算符号等。
		for (int i=65; i<=90; i++) {
			outputStream.write(i);
		}
		
		// 把 a ~ z 写入文件
		for (int i = 'a'; i < 'z'; i++) {
			outputStream.write(i);
		}
		
		// 把 1 个字节范围（-128~127）写入文件
		for (int i=-128; i<=127; i++) {
			outputStream.write(i);
		}
		
		// 写一个字节数组
		String str = "我爱编程";
		outputStream.write(str.getBytes("UTF-8"));
		outputStream.write(str.getBytes("GBK"));
		outputStream.close();
	}
	
	/**
	 * 测试字节流读取
	 * 一个字节一个字节读取
	 * @throws IOException
	 */
	@Test
	public void testInputStream () throws IOException {
		File file = new File("D:/java/linkknown/io.txt");
		InputStream inputStream = new FileInputStream(file);
		
		int byteData;
		/*
		 * InputStream.read()返回一个 unsigned byte[0 ~ 255]
		 * byte的范围是[-128、127]，所以如果read()返回的数在[128、255]的范围内时，则表示负数，即
		 * (byte)128=-128
		 * (byte)129=-127
		 * (byte)255=-1
		 */
		while ((byteData = inputStream.read()) != -1) {
			System.out.println(byteData);
		}
		
		System.out.println("一共 " + file.length() + " 字节");
		
		inputStream.close();
	}
	
	/**
	 * 读取一个字节数组,一次读取 10 个
	 */
	@Test
	public void testInputStream2 () throws IOException {
		File file = new File("D:/java/linkknown/io.txt");
		InputStream inputStream = new FileInputStream(file);

		byte[] realBytes = new byte[(int) file.length()];
		
		int len = 0, i = 0;
		byte[] bytes = new byte[10];
		
		while ((len = inputStream.read(bytes)) != -1) {
			System.out.println("读取了" + len + "个字节" + Arrays.toString(bytes));
			
			System.arraycopy(bytes, 0, realBytes, i * 10, len);   // 实际读取的是 len 个长度，不一定就是 10 个
			
			i++;
		}
		
		System.out.println("一共 " + file.length() + " 字节");
		System.out.println(new String(realBytes, "GBK"));
		System.out.println(new String(realBytes, "UTF-8"));
		
		inputStream.close();
	}
	
	/**
	 * 一次性全部读取出来
	 * @throws IOException
	 */
	@Test
	public void testInputStream3 () throws IOException {
		File file = new File("D:/java/linkknown/io.txt");
		InputStream inputStream = new FileInputStream(file);

		byte[] realBytes = new byte[(int) file.length()];
		
		while (inputStream.read(realBytes) != -1) {
			System.out.println("一共 " + file.length() + " 字节");
			System.out.println(new String(realBytes, "GBK"));
			System.out.println(new String(realBytes, "UTF-8"));
		}
		
		inputStream.close();
	}
	
	
	/**
	 * 测试字符流读取（文件复制）
	 * 一次读取一个字节数组
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
		while ((len = inputStream.read(bytes)) != -1) {		// len 表示实际读取的数量 -1 代表没有更多数据
			outputStream.write(bytes, 0, len);
		}
		inputStream.close();
		outputStream.close();
	}
	
	
	/**
	 * 追加模式
	 * public FileOutputStream(String name, boolean append) append 为 true 时表示追加
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
