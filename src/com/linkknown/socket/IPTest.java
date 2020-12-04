package com.linkknown.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

public class IPTest {
	
	/**
	 * ����У�� ipV4
	 */
	@Test
	public void testIP () {
		String ip = "192.168.1.10";
		
		/*
		 * ÿһ�����ֿ������У�
		 * 		25[0-5]
		 * 		2[0-4]\\d
		 * 		[0-1]?\\d?\\d
		 */
		System.out.println(ip.matches("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$"));
	}

	/**
	 * ��ӡ ipV4 �� ipV6 ����
	 */
	@Test
	public void testIP2 () {
		// Ϊʲô�������� 0 ?
		System.out.println(256 * 256 * 256 * 256);
		// l ��ʾ long ��
		System.out.println(256 * 256 * 256 * 256l);	// ipV4 ֻ�� 4294967296 ��42�ڣ�����ַ
		System.out.println(Math.pow(256, 4));
		System.out.println(Math.pow(256, 16));
	}
	
	/**
	 * ip ����
	 */
	@Test
	public void testIP3 () throws UnknownHostException {
		InetAddress address = InetAddress.getByName("www.baidu.com");
		System.out.println(address);
		System.out.println(address.getHostName());
		System.out.println(address.getHostAddress());	// ip ��ַ
		
		address = InetAddress.getLocalHost();
		System.out.println(address);
		System.out.println(address.getHostName());
		System.out.println(address.getHostAddress());	// ip ��ַ
	}
}
