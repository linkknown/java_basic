package com.linkknown.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
		List<ConsumerRecord> consumerRecords = ConsumerRecord.getRandomInstance(100);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	// 日期格式化
		DecimalFormat df = new DecimalFormat("00000000.00");		// 数字的格式化
		
		// 其中mode对应的字符串为:
		// r:只读模式 rw:读写模式
		RandomAccessFile randomAccessFile = new RandomAccessFile("D:/io8.txt", "rw");
		for (int i = 0; i < consumerRecords.size(); i++) {
			ConsumerRecord consumerRecord = consumerRecords.get(i);
			
			randomAccessFile.writeBytes(consumerRecord.getUserId());	// 写入用户 id
			randomAccessFile.writeBytes(sdf.format(consumerRecord.getConsumerDate()));	// 写入当前时间
			randomAccessFile.writeBytes(df.format(consumerRecord.getConsumerAmount()));	// 写入用户消费金额
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
		String singleData = "00000000002020-09-14 11:13:0400000000.00";
		
		String userId = "0000000000";
		String consumerDate = "2020-09-14 11:13:04";
		String consumerAmount = "00000000.00";
		
		RandomAccessFile randomAccessFile = new RandomAccessFile("D:/io.txt", "r");
		
		// buff 存储一个用户的消费信息
		byte[] buff_userId = new byte[userId.getBytes().length];
		byte[] buff_consumerDate = new byte[consumerDate.getBytes().length];
		byte[] buff_consumerAmount = new byte[consumerAmount.getBytes().length];
		
		// 将文件记录指针定位到 pos 位置
		// 从起始位置开始读取
		randomAccessFile.seek(0);
		
		while (randomAccessFile.read(buff_userId) != -1 && randomAccessFile.read(buff_consumerDate) != -1 
				&& randomAccessFile.read(buff_consumerAmount) != -1) {
			System.out.println(new String(buff_userId) + " ~ " + new String(buff_consumerDate) + " ~ " + new String(buff_consumerAmount));
			
		}
		
		randomAccessFile.close();
	}

	
	/**
	 * 只读取用户 id
	 * @throws IOException
	 */
	@Test
	public void testRandomAccessFileRead2 () throws IOException {
		String singleData = "00000000002020-09-14 11:13:0400000000.00";
		
		String userId = "0000000000";
		String consumerDate = "2020-09-14 11:13:04";
		String consumerAmount = "00000000.00";
		
		RandomAccessFile randomAccessFile = new RandomAccessFile("D:/io.txt", "r");
		
		byte[] buff_userId = new byte[userId.getBytes().length];
//		byte[] buff_consumerDate = new byte[consumerDate.getBytes().length];
//		byte[] buff_consumerAmount = new byte[consumerAmount.getBytes().length];
		
		// 从起始位置开始读取
		randomAccessFile.seek(0);
		
		while (randomAccessFile.read(buff_userId) != -1/* && randomAccessFile.read(buff_consumerDate) != -1 
				&& randomAccessFile.read(buff_consumerAmount) != -1*/) {
			
			// 调往下一个指针继续读取
			randomAccessFile.seek(randomAccessFile.getFilePointer() - userId.getBytes().length + singleData.getBytes().length);
			
			System.out.println(new String(buff_userId) /*+ " ~ " + new String(buff_consumerDate) + " ~ " + new String(buff_consumerAmount)*/);
			
		}
		
		randomAccessFile.close();
	}
}
