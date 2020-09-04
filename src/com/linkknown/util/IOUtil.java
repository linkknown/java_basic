package com.linkknown.util;

import java.io.Closeable;
import java.io.IOException;

public class IOUtil {

	public static void close(Closeable... closeables) {
		for (Closeable closeable : closeables) {
			if (closeable != null) {
				try {
					closeable.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
