package com.linkknown.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

public class IPTest {

	/**
	 * 打印 ipV4 和 ipV6 数量
	 */
	@Test
	public void testIp () {
		// 为什么溢出后等于 0 ?
		System.out.println(256 * 256 * 256 * 256);
		// l 表示 long 型
		System.out.println(256 * 256 * 256 * 256l);	// ipV4 只有 4294967296 （42亿）个地址
		System.out.println(Math.pow(256, 4));
		
		System.out.println(Math.pow(256, 16));
	}
	
	/**
	 * ip 测试
	 * @throws UnknownHostException
	 */
	@Test
	public void testIp2 () throws UnknownHostException {
		InetAddress address = InetAddress.getByName("www.baidu.com");
		System.out.println(address);
		System.out.println(address.getHostName());
		System.out.println(address.getHostAddress());
		
		address = InetAddress.getLocalHost();
		System.out.println(address);
		System.out.println(address.getHostName());
		System.out.println(address.getHostAddress());
	}
}
