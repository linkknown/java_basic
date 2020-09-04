package com.linkknown.other;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

public class HashTest {
	/**
	 * java��ȡ�ļ���md5ֵ
	 * 
	 * @param fis ������
	 * @return
	 */
	public static String md5HashCode(InputStream fis) {
		try {
			// �õ�һ��MD5ת����,�����ʹ��SHA-1��SHA-256������SHA-1,SHA-256
			MessageDigest md = MessageDigest.getInstance("MD5");

			// �ֶ�ν�һ���ļ����룬���ڴ����ļ����ԣ��Ƚ��Ƽ����ַ�ʽ��ռ���ڴ�Ƚ��١�
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fis.read(buffer, 0, 1024)) != -1) {
				md.update(buffer, 0, length);
			}
			fis.close();
			// ת�������ذ���16��Ԫ���ֽ�����,������ֵ��ΧΪ-128��127
			byte[] md5Bytes = md.digest();
			BigInteger bigInt = new BigInteger(1, md5Bytes);// 1�������ֵ
			return bigInt.toString(16);// ת��Ϊ16����
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@Test
	public void testHash () throws FileNotFoundException {
		System.out.println(HashTest.md5HashCode(new FileInputStream("D:/io.txt")));
	}
}
