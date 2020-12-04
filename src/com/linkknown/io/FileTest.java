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
 * 文件相关测试类
 * 
 * @author Administrator
 *
 */
public class FileTest {
	
    /**
     * 测试 File 对象获取的几种方式
     * @throws URISyntaxException
     */
    @Test
    public void testFile () throws URISyntaxException {
        // window 系统的路径分隔符是 \, Linux 系统的路径分隔符是 /, \需要进行转义
        File file = new File("D:\\java\\linkknown\\helloworld.txt");
        
        System.out.println(file.exists());
        
        // window 系统也可以使用 /
        file = new File("D:/java/linkknown/helloworld.txt");
        
        System.out.println(file.exists());
        
        // 使用 File.separator 路径分隔符
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
	 * File 静态属性、静态方法测试
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
	 * 创建文件：错误方式
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
	 * 创建文件：正确方式
	 * @throws IOException
	 */
	@Test
	public void testCreateFile2 () throws IOException {
		File file = new File("D:/java/linkknown/helloworld.txt");
		if (!file.exists()) {
			// 递归创建父目录
			mkdirs(file.getParentFile());
			// 创建当前文件
			file.createNewFile();
		}
	}
	
	/**
	 * 递归的重要前提是能跳出去
	 * @param file
	 */
	public static void mkdirs(File file) {
		if (!file.exists()) {
			mkdirs(file.getParentFile());
			
			file.mkdir();
		} 
	}
	
	/**
	 * 改进
	 * @throws IOException
	 */
	@Test
	public void testCreateFile3 () throws IOException {
		File file = new File("D:/java/linkknown/helloworld.txt");
		if (!file.exists()) {
			if (file.getParentFile() != null) {
				file.getParentFile().mkdirs();
			}
			// 创建当前文件
			file.createNewFile();
		}
	}
	
	

	/**
	 * 测试文件 rename
	 */
	@Test
	public void testRenameFile () {
		File file = new File("D:/java/linkknown/helloworld.txt");
		File file2 = new File("D:/java/linkknown/helloworld2.txt");
		
		boolean flag = file.renameTo(file2);		// 重命名文件
		System.out.println(flag);
		
		File file3 = new File("D:/java/linkknown2/helloworld2.txt");
		flag = file2.renameTo(file3);				// 移动文件（失败）
		System.out.println(flag);
	
		// 需要创建父级目录
		file3.getParentFile().mkdirs();
		flag = file2.renameTo(file3);				// 移动文件（成功）
		System.out.println(flag);
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
		Date date = new Date(lastmodified);
		SimpleDateFormat simpledataformat = new SimpleDateFormat("YY年MM月DD日 HH:mm:ss");
		System.out.println("最后一次修改的时间是：" + simpledataformat.format(date));
	}

	/**
	 * 递归遍历文件夹
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
	 * 測試递归遍历文件夹
	 */
	@Test
	public void testListDir() {
		listDir(new File("D:\\zhourui\\program\\java\\IDEA\\java_basic"), "");
	}

	/**
	 * 文件过滤
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
	 * FileFilter 的使用,只过滤文件夹
	 */
	@Test
	public void testListDirWithFilter() {
		/**
		 * 过滤目录
		 */
		listDirWidthFilter(new File("D:\\zhourui\\program\\java\\IDEA\\java_basic"), "", new FileFilter() {

			@Override
			public boolean accept(File file) {
				return file.isDirectory();
			}
		});
	}
}
