package com.linkknown.exception;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

/**
 * �쳣����
 * 
 * @author Administrator
 *
 */
public class HandleExceptionTest {

	/**
	 * try catch �����쳣
	 */
	@Test
	public void testIOException() {
		FileWriter fw = null;
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			fw = new FileWriter("D:/helloworld.txt");
			fw.write(ip);
		} catch (UnknownHostException e) {			// UnknownHostException extends IOException
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	/**
	 * �׳��쳣
	 * @throws UnknownHostException 
	 */
	private static String getLocalIp () throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
	
	/**
	 * �����쳣�������׳�
	 * @throws UnknownHostException 
	 */
	private static String getLocalIp2 () throws UnknownHostException {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			throw e;
		}
	}
	
	/**
	 * �����쳣�������׳�
	 * @throws Exception 
	 */
	private static String getLocalIp3 () throws Exception {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Test
	public void testGetLocalIp () throws Exception {
		System.out.println(getLocalIp());
		System.out.println(getLocalIp2());
		System.out.println(getLocalIp3());
	}
}
