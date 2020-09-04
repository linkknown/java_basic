package com.linkknown.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * �ļ���ز�����
 * 
 * @author Administrator
 *
 */
public class FileTest {

	/**
	 * File ��̬��Ϥ����
	 */
	@Test
	public void test() {
		System.out.println(File.separator);
		System.out.println(File.pathSeparator);
		System.out.println(File.separatorChar);
		System.out.println(File.pathSeparatorChar);

		File[] rootFiles = File.listRoots();
		for (File file : rootFiles) {
			System.out.println(file.getAbsolutePath());
		}
	}

	/**
	 * �ļ�������������
	 * 
	 * @throws IOException
	 */
	@Test
	public void testCreate() throws IOException {
		File file = new File("D:\\java\\linkknown\\helloworld.txt");
		System.out.println("�ļ��Ƿ񴴽��ɹ���" + file.createNewFile());
		System.out.println("�����ļ����Ƿ񴴽��ɹ���" + file.mkdir());
		System.out.println("�༶�ļ����Ƿ񴴽��ɹ���" + file.mkdirs());

		File dest = new File("D:/java/linkknown/helloworld2.txt");
		System.out.println("�ļ��������Ƿ�ɹ���" + file.renameTo(dest));
	}

	/**
	 * �ļ�ɾ������
	 */
	@Test
	public void testDelete() {
		File file = new File("D:/java/linkknown/helloworld2.txt");
		System.out.println("�ļ��Ƿ�ɾ���ɹ���" + file.delete());
		/**
		 * deleteOnExit ������˼�� �� ������ɾ��������Ϊ��ô�򵥣���Ҫ�������Ի���, ��������ʱ,�൱�ڶ�deleteOnExit����һ��������,
		 * ��������ִ��ɾ������, ���ǳ������н���, JVM��ֹʱ����������ɾ�����������÷����ǽ�ɾ�����������һ�£�������ֹͣ��ʱ���ٽ��в�����
		 * 
		 * ������ʹ��
		 */
		file.deleteOnExit();
	}

	/**
	 * �ļ��жϲ���
	 */
	@Test
	public void testCheck() {
		File file = new File("D:/java/linkknown/helloworld2.txt");
		System.out.println("�ļ����ļ����Ƿ���ڣ�" + file.exists());
		System.out.println("�Ƿ���һ���ļ���" + file.isFile());
		System.out.println("�Ƿ���һ���ļ��У�" + file.isDirectory());
		System.out.println("�ļ��Ƿ��ִ�У�" + file.canExecute());
		System.out.println("�ļ��Ƿ�ɶ���" + file.canRead());
		System.out.println("�ļ��Ƿ��д��" + file.canWrite());
		System.out.println("�ļ��Ƿ����أ�" + file.isHidden());
		System.out.println("�Ƿ��Ǿ���·����" + file.isAbsolute());
	}

	/**
	 * �ļ���Ϣ��ȡ����
	 */
	@Test
	public void testGetFileInfo() {
		File file = new File("D:/java/linkknown/helloworld2.txt");
		System.out.println("�ļ������ļ��е������ǣ�" + file.getName());
		System.out.println("����·���ǣ�" + file.getPath());
		System.out.println("����·���ǣ�" + file.getAbsolutePath());
		System.out.println("�ļ���С�ǣ����ֽ�Ϊ��λ��:" + file.length());
		System.out.println("��·����" + file.getParent());

		// ʹ�������������ڸ�ʽ������л�ȡ�涨��ʱ��
		long lastmodified = file.lastModified();
		Date data = new Date(lastmodified);
		SimpleDateFormat simpledataformat = new SimpleDateFormat("YY��MM��DD�� HH:mm:ss");
		System.out.println("���һ���޸ĵ�ʱ���ǣ�" + simpledataformat.format(data));
	}

	/**
	 * �ݹ�����ļ���
	 */
	void listDir(File dirFile, String indent) {
		File[] files = dirFile.listFiles();
		if (files != null) {
			for (File file : files) {
				System.out.println(indent + file.getName());
				if (file.isDirectory()) {
					listDir(file, "|--" + indent);
				}
			}
		}
	}

	/**
	 * �yԇ�ݹ�����ļ���
	 */
	@Test
	public void testListDir() {
		listDir(new File("E:\\teacher\\code"), "");
	}

	/**
	 * �ļ�����
	 */
	void listDirWidthFilter(File dirFile, String indent, FileFilter filter) {
		File[] files = dirFile.listFiles(filter);
		if (files != null) {
			for (File file : files) {
				System.out.println(indent + file.getName());
				if (file.isDirectory()) {
					listDirWidthFilter(file, "|--" + indent, filter);
				}
			}
		}
	}

	@Test
	public void testListDirWithFilter() {
		/**
		 * ����Ŀ¼
		 */
		listDirWidthFilter(new File("E:\\teacher\\code"), "", new FileFilter() {

			@Override
			public boolean accept(File file) {
				return file.isDirectory();
			}
		});
	}
}
