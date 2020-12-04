package com.linkknown.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
     * ���� File �����ȡ�ļ��ַ�ʽ
     * @throws URISyntaxException
     */
    @Test
    public void testFile () throws URISyntaxException {
        // window ϵͳ��·���ָ����� \, Linux ϵͳ��·���ָ����� /, \��Ҫ����ת��
        File file = new File("D:\\java\\linkknown\\helloworld.txt");
        
        System.out.println(file.exists());
        
        // window ϵͳҲ����ʹ�� /
        file = new File("D:/java/linkknown/helloworld.txt");
        
        System.out.println(file.exists());
        
        // ʹ�� File.separator ·���ָ���
        System.out.println("D:" + File.separator + "java" + File.separator + "linkknown" + File.separator + "helloworld.txt");
        System.out.println(file.exists());
        
        System.out.println(file.toURI());
        
        file = new File(new URI("file:/D:/java/linkknown/helloworld.txt"));
        System.out.println(file.exists());
    
        file = new File(new File("D:" + File.separator + "java" + File.separator + "linkknown"), "helloworld.txt");
        System.out.println(file.exists());
    
        file = new File("D:" + File.separator + "java" + File.separator + "linkknown", "helloworld.txt");
        System.out.println(file.exists());
    }
	

	/**
	 * File ��̬���ԡ���̬��������
	 */
	@Test
	public void testListRoots() {
		System.out.println(File.separator);

		File[] rootFiles = File.listRoots();
		for (File file : rootFiles) {
			System.out.println(file.getAbsolutePath());
		}
	}

	/**
	 * �����ļ�������ʽ
	 * @throws IOException
	 */
	@Test
	public void testCreateFile () throws IOException {
		File file = new File("D:/java/linkknown/helloworld.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
	}
	
	/**
	 * �����ļ�����ȷ��ʽ
	 * @throws IOException
	 */
	@Test
	public void testCreateFile2 () throws IOException {
		File file = new File("D:/java/linkknown/helloworld.txt");
		if (!file.exists()) {
			// �ݹ鴴����Ŀ¼
			mkdirs(file.getParentFile());
			// ������ǰ�ļ�
			file.createNewFile();
		}
	}
	
	/**
	 * �ݹ����Ҫǰ����������ȥ
	 * @param file
	 */
	public static void mkdirs(File file) {
		if (!file.exists()) {
			mkdirs(file.getParentFile());
			
			file.mkdir();
		} 
	}
	
	/**
	 * �Ľ�
	 * @throws IOException
	 */
	@Test
	public void testCreateFile3 () throws IOException {
		File file = new File("D:/java/linkknown/helloworld.txt");
		if (!file.exists()) {
			if (file.getParentFile() != null) {
				file.getParentFile().mkdirs();
			}
			// ������ǰ�ļ�
			file.createNewFile();
		}
	}
	
	

	/**
	 * �����ļ� rename
	 */
	@Test
	public void testRenameFile () {
		File file = new File("D:/java/linkknown/helloworld.txt");
		File file2 = new File("D:/java/linkknown/helloworld2.txt");
		
		boolean flag = file.renameTo(file2);		// �������ļ�
		System.out.println(flag);
		
		File file3 = new File("D:/java/linkknown2/helloworld2.txt");
		flag = file2.renameTo(file3);				// �ƶ��ļ���ʧ�ܣ�
		System.out.println(flag);
	
		// ��Ҫ��������Ŀ¼
		file3.getParentFile().mkdirs();
		flag = file2.renameTo(file3);				// �ƶ��ļ����ɹ���
		System.out.println(flag);
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
		Date date = new Date(lastmodified);
		SimpleDateFormat simpledataformat = new SimpleDateFormat("YY��MM��DD�� HH:mm:ss");
		System.out.println("���һ���޸ĵ�ʱ���ǣ�" + simpledataformat.format(date));
	}

	/**
	 * �ݹ�����ļ���
	 */
	void listDir(File dirFile, String indent) {
		if (dirFile.isFile() || !dirFile.exists()) {
			return;
		}
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
		listDir(new File("D:\\zhourui\\program\\java\\IDEA\\java_basic"), "");
	}

	/**
	 * �ļ�����
	 */
	void listDirWidthFilter(File dirFile, String indent, FileFilter filter) {
		if (dirFile.isFile() || !dirFile.exists()) {
			return;
		}
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

	/**
	 * FileFilter ��ʹ��,ֻ�����ļ���
	 */
	@Test
	public void testListDirWithFilter() {
		/**
		 * ����Ŀ¼
		 */
		listDirWidthFilter(new File("D:\\zhourui\\program\\java\\IDEA\\java_basic"), "", new FileFilter() {

			@Override
			public boolean accept(File file) {
				return file.isDirectory();
			}
		});
	}
}
