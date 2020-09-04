package com.linkknown.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	// ���ڸ�ʽ��
		DecimalFormat df = new DecimalFormat("00000000.00");		// ���ֵĸ�ʽ��
		
		// ����mode��Ӧ���ַ���Ϊ:
		// r:ֻ��ģʽ rw:��дģʽ
		RandomAccessFile randomAccessFile = new RandomAccessFile("D:/io8.txt", "rw");
		for (int i = 0; i < 100; i++) {
			// 0 ����ǰ�油��0
			// 10 ������Ϊ10
			// d �������Ϊ������
			randomAccessFile.writeBytes(String.format("%010d", i));		// д���û� id
			randomAccessFile.writeBytes(sdf.format(new Date()));		// д�뵱ǰʱ��
			randomAccessFile.writeBytes(df.format(i));					// д���û����ѽ��
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
		String singleData = "0000000000yyyy-MM-dd HH:mm:ss00000000.00";
		
		RandomAccessFile randomAccessFile = new RandomAccessFile("D:/io8.txt", "r");
		int pos = 0;
		randomAccessFile.seek(pos);	// ���ļ���¼ָ�붨λ�� pos λ��
		int len;
		byte[] buff = new byte[singleData.getBytes().length];	// buff �洢һ���û���������Ϣ
		while ((len = randomAccessFile.read(buff)) != -1) {
			System.out.println(new String(buff, 0, len));
			pos += singleData.getBytes().length;
			randomAccessFile.seek(pos);		// ������һ���û���¼��ʼλ��
		}
		randomAccessFile.close();
	}
}
