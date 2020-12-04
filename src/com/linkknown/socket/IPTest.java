package com.linkknown.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

public class IPTest {
	
	/**
	 * 正则校验 ipV4
	 */
	@Test
	public void testIP () {
		String ip = "192.168.1.10";
		
		/*
		 * 每一段数字可能性有：
		 * 		25[0-5]
		 * 		2[0-4]\\d
		 * 		[0-1]?\\d?\\d
		 */
		System.out.println(ip.matches("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$"));
	}

	/**
	 * 打印 ipV4 和 ipV6 数量
	 */
	@Test
	public void testIP2 () {
		// 为什么溢出后等于 0 ?
		System.out.println(256 * 256 * 256 * 256);
		// l 表示 long 型
		System.out.println(256 * 256 * 256 * 256l);	// ipV4 只有 4294967296 （42亿）个地址
		System.out.println(Math.pow(256, 4));
		System.out.println(Math.pow(256, 16));
	}
	
	/**
	 * ip 测试
	 */
	@Test
	public void testIP3 () throws UnknownHostException {
		InetAddress address = InetAddress.getByName("www.baidu.com");
		System.out.println(address);
		System.out.println(address.getHostName());
		System.out.println(address.getHostAddress());	// ip 地址
		
		address = InetAddress.getLocalHost();
		System.out.println(address);
		System.out.println(address.getHostName());
		System.out.println(address.getHostAddress());	// ip 地址
	}
}
