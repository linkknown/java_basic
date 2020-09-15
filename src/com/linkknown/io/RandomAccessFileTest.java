package com.linkknown.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * RandomAccessFile ��
 * 
 * @author Administrator
 *
 */
public class RandomAccessFileTest {

	/**
	 * ��¼�û���ֵ���ѽ����־
	 * ������������ļ� RandomAccessFile ��
	 * @throws IOException
	 */
	@Test
	public void testRandomAccessFile() throws IOException {
		List<ConsumerRecord> consumerRecords = ConsumerRecord.getRandomInstance(100);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	// ���ڸ�ʽ��
		DecimalFormat df = new DecimalFormat("00000000.00");		// ���ֵĸ�ʽ��
		
		// ����mode��Ӧ���ַ���Ϊ:
		// r:ֻ��ģʽ rw:��дģʽ
		RandomAccessFile randomAccessFile = new RandomAccessFile("D:/io8.txt", "rw");
		for (int i = 0; i < consumerRecords.size(); i++) {
			ConsumerRecord consumerRecord = consumerRecords.get(i);
			
			randomAccessFile.writeBytes(consumerRecord.getUserId());	// д���û� id
			randomAccessFile.writeBytes(sdf.format(consumerRecord.getConsumerDate()));	// д�뵱ǰʱ��
			randomAccessFile.writeBytes(df.format(consumerRecord.getConsumerAmount()));	// д���û����ѽ��
		}
		randomAccessFile.close();
	}
	
	/**
	 * ��ӡ���е��û�������Ϣ
	 * ������ϰ���ֱ��ӡ�û� id�� �û���ֵʱ�䣬�û����Ѽ�¼
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
		
		// buff �洢һ���û���������Ϣ
		byte[] buff_userId = new byte[userId.getBytes().length];
		byte[] buff_consumerDate = new byte[consumerDate.getBytes().length];
		byte[] buff_consumerAmount = new byte[consumerAmount.getBytes().length];
		
		// ���ļ���¼ָ�붨λ�� pos λ��
		// ����ʼλ�ÿ�ʼ��ȡ
		randomAccessFile.seek(0);
		
		while (randomAccessFile.read(buff_userId) != -1 && randomAccessFile.read(buff_consumerDate) != -1 
				&& randomAccessFile.read(buff_consumerAmount) != -1) {
			System.out.println(new String(buff_userId) + " ~ " + new String(buff_consumerDate) + " ~ " + new String(buff_consumerAmount));
			
		}
		
		randomAccessFile.close();
	}

	
	/**
	 * ֻ��ȡ�û� id
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
		
		// ����ʼλ�ÿ�ʼ��ȡ
		randomAccessFile.seek(0);
		
		while (randomAccessFile.read(buff_userId) != -1/* && randomAccessFile.read(buff_consumerDate) != -1 
				&& randomAccessFile.read(buff_consumerAmount) != -1*/) {
			
			// ������һ��ָ�������ȡ
			randomAccessFile.seek(randomAccessFile.getFilePointer() - userId.getBytes().length + singleData.getBytes().length);
			
			System.out.println(new String(buff_userId) /*+ " ~ " + new String(buff_consumerDate) + " ~ " + new String(buff_consumerAmount)*/);
			
		}
		
		randomAccessFile.close();
	}
}
