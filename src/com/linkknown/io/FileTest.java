package com.linkknown.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * 文件相关测试类
 * 
 * @author Administrator
 *
 */
public class FileTest {

	/**
	 * File 静态熟悉测试
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
	 * 文件创建方法测试
	 * 
	 * @throws IOException
	 */
	@Test
	public void testCreate() throws IOException {
		File file = new File("D:\\java\\linkknown\\helloworld.txt");
		System.out.println("文件是否创建成功：" + file.createNewFile());
		System.out.println("单级文件夹是否创建成功：" + file.mkdir());
		System.out.println("多级文件夹是否创建成功：" + file.mkdirs());

		File dest = new File("D:/java/linkknown/helloworld2.txt");
		System.out.println("文件重命名是否成功：" + file.renameTo(dest));
	}

	/**
	 * 文件删除测试
	 */
	@Test
	public void testDelete() {
		File file = new File("D:/java/linkknown/helloworld2.txt");
		System.out.println("文件是否删除成功：" + file.delete());
		/**
		 * deleteOnExit ：顾名思义 ： 存在则删除。你以为这么简单？不要被别面迷惑了, 它被调用时,相当于对deleteOnExit做了一个生声明,
		 * 不会马上执行删除操作, 而是程序运行结束, JVM终止时才真正调用删除操作。即该方法是将删除的命令缓存了一下，到服务停止的时候再进行操作！
		 * 
		 * 不建议使用
		 */
		file.deleteOnExit();
	}

	/**
	 * 文件判断测试
	 */
	@Test
	public void testCheck() {
		File file = new File("D:/java/linkknown/helloworld2.txt");
		System.out.println("文件或文件夹是否存在：" + file.exists());
		System.out.println("是否是一个文件：" + file.isFile());
		System.out.println("是否是一个文件夹：" + file.isDirectory());
		System.out.println("文件是否可执行：" + file.canExecute());
		System.out.println("文件是否可读：" + file.canRead());
		System.out.println("文件是否可写：" + file.canWrite());
		System.out.println("文件是否隐藏：" + file.isHidden());
		System.out.println("是否是绝对路径：" + file.isAbsolute());
	}

	/**
	 * 文件信息获取方法
	 */
	@Test
	public void testGetFileInfo() {
		File file = new File("D:/java/linkknown/helloworld2.txt");
		System.out.println("文件或者文件夹得名称是：" + file.getName());
		System.out.println("绝对路径是：" + file.getPath());
		System.out.println("绝对路径是：" + file.getAbsolutePath());
		System.out.println("文件大小是（以字节为单位）:" + file.length());
		System.out.println("父路径是" + file.getParent());

		// 使用日期类与日期格式化类进行获取规定的时间
		long lastmodified = file.lastModified();
		Date data = new Date(lastmodified);
		SimpleDateFormat simpledataformat = new SimpleDateFormat("YY年MM月DD日 HH:mm:ss");
		System.out.println("最后一次修改的时间是：" + simpledataformat.format(data));
	}

	/**
	 * 递归遍历文件夹
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
	 * 測試递归遍历文件夹
	 */
	@Test
	public void testListDir() {
		listDir(new File("E:\\teacher\\code"), "");
	}

	/**
	 * 文件过滤
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
		 * 过滤目录
		 */
		listDirWidthFilter(new File("E:\\teacher\\code"), "", new FileFilter() {

			@Override
			public boolean accept(File file) {
				return file.isDirectory();
			}
		});
	}
}
