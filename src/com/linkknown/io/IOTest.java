package com.linkknown.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.junit.jupiter.api.Test;

import com.linkknown.util.IOUtil;

public class IOTest {

	/**
	 * дһ���ļ��еݹ鸴�ƹ��ܣ��ݹ鿽���ļ���
	 * �������ر�˳��
	 * �ۺ���ϰ
	 */
	@Test
	public void testIO() {

		copyDir("D:\\zhourui\\program\\java\\IDEA\\java_basic", "D:\\test\\java_basic");
	}

	public static void copyDir(String dirPath, String destDirPath) {
		if (dirPath == null || dirPath.trim() == "") {
			return;
		}

		File dirFile = new File(dirPath);
		if (dirFile.isFile()) {
			return;
		}

		File[] files = dirFile.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				copyFile(file.getAbsolutePath(), destDirPath + File.separator + file.getName());
			} else {
				copyDir(file.getAbsolutePath(), destDirPath + File.separator + file.getName());
			}
		}
	}

	private static void copyFile(String srcFilePath, String destFilePath) {
		
		// ����Ŀ¼
		new File(destFilePath).getParentFile().mkdirs();
		
		InputStream in = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;

		OutputStream out = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter writer = null;

		try {
			in = new FileInputStream(srcFilePath);
			inputStreamReader = new InputStreamReader(in);
			reader = new BufferedReader(inputStreamReader);

			out = new FileOutputStream(destFilePath);
			outputStreamWriter = new OutputStreamWriter(out);
			writer = new BufferedWriter(outputStreamWriter);

			int len = 0;
			char[] chars = new char[1000];
			while ((len = reader.read(chars)) != -1) {
				writer.write(chars, 0, len);

				// writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("�ļ�����ʧ������");
		} finally {
			IOUtil.close(writer, outputStreamWriter, out, reader, inputStreamReader, in);
		}
	}
}
