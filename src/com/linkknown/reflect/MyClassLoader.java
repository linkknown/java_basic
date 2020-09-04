package com.linkknown.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 自定义类加载器
 * @author Administrator
 *
 */
public class MyClassLoader extends ClassLoader {

	private String classFilePath;

	public MyClassLoader(String classFilePath) {
		super();
		this.classFilePath = classFilePath;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {

		File file = new File(classFilePath);

		try {
			byte[] bytes = readFileAsBytes(file);
			Class<?> defineClass = this.defineClass(name, bytes, 0, bytes.length);

			return defineClass;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.findClass(name);

	}

	private byte[] readFileAsBytes(File file) throws IOException {
		FileInputStream inputStream = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		inputStream.read(bytes);
		inputStream.close();
		return bytes;
	}
}
