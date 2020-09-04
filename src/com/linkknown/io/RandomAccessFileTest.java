package com.linkknown.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * RandomAccessFile 类
 * 
 * @author Administrator
 *
 */
public class RandomAccessFileTest {

	/**
	 * 记录用户充值消费金额日志
	 * 测试随机访问文件 RandomAccessFile 类
	 * @throws IOException
	 */
	@Test
	public void testRandomAccessFile() throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	// 日期格式化
		DecimalFormat df = new DecimalFormat("00000000.00");		// 数字的格式化
		
		// 其中mode对应的字符串为:
		// r:只读模式 rw:读写模式
		RandomAccessFile randomAccessFile = new RandomAccessFile("D:/io8.txt", "rw");
		for (int i = 0; i < 100; i++) {
			// 0 代表前面补充0
			// 10 代表长度为10
			// d 代表参数为正数型
			randomAccessFile.writeBytes(String.format("%010d", i));		// 写入用户 id
			randomAccessFile.writeBytes(sdf.format(new Date()));		// 写入当前时间
			randomAccessFile.writeBytes(df.format(i));					// 写入用户消费金额
		}
		randomAccessFile.close();
	}
	
	/**
	 * 打印所有的用户消费信息
	 * 随堂练习：分别打印用户 id， 用户充值时间，用户消费记录
	 * @return
	 * @throws IOException 
	 */
	@Test
	public void testRandomAccessFileRead () throws IOException {
		String singleData = "0000000000yyyy-MM-dd HH:mm:ss00000000.00";
		
		RandomAccessFile randomAccessFile = new RandomAccessFile("D:/io8.txt", "r");
		int pos = 0;
		randomAccessFile.seek(pos);	// 将文件记录指针定位到 pos 位置
		int len;
		byte[] buff = new byte[singleData.getBytes().length];	// buff 存储一个用户的消费信息
		while ((len = randomAccessFile.read(buff)) != -1) {
			System.out.println(new String(buff, 0, len));
			pos += singleData.getBytes().length;
			randomAccessFile.seek(pos);		// 跳到下一个用户记录开始位置
		}
		randomAccessFile.close();
	}
}
