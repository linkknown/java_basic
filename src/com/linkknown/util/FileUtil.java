package com.linkknown.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 文件工具类
 * 
 * @author Administrator
 *
 */
public class FileUtil {

	public static void writeStringToFile(String str, File file) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			writer.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtil.close(writer);
		}
	}
}
