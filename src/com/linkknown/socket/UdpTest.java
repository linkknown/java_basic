package com.linkknown.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;

import org.junit.jupiter.api.Test;

/**
 * @author Administrator
 * UDP 传输数据就好像在大海上运输货物, DatagramSocket 就像的两个通信港口的码头, DatagramPacket 就像在两端运输货物的轮船
 * 
 * 发送端（send）
 * 		创建DatagramSocket,随机端口号
 * 		创建DatagramPacket,制定数据，长度，地址和端口
 * 		使用DatagramSocket发送DatagramPacket
 * 		关闭DatagramSocket
 * 		异常处理
 * 
 * 
 * 接收端（receive）
 * 		创建DatagramSocket,匹配端口号
 * 		创建DatagramPacket，创建接收数据和有效数据长度
 * 		接收socket
 * 		接收packet
 * 		关闭DatagramSocket
 */
public class UdpTest {

	@Test
	public void testServer () throws IOException {

		// 创建服务端套接字（服务端码头）,需要提供端口号,会在当前 IP 上自动绑定端口号
		DatagramSocket socket = new DatagramSocket(9000);
		
		byte[] buff = new byte[100];
		// 创建服务端接收的数据包（轮船，指定接收容量）
		DatagramPacket packet = new DatagramPacket(buff, buff.length);
		
		System.out.println("开始等待消息...");
		
		// 接收
		socket.receive(packet); 	// 没收到数据就会一直阻塞等待
		
		System.out.println("接收到了消息...");
		
		// 消息的实际长度
		int length = packet.getLength();
		String str = new String(buff, 0, length);
		System.out.println("收到了消息：" + str);
	
		// 发送回信
		String returnStr = "感谢你的来信";
		byte[] bytes = returnStr.getBytes();
		
		// 创建服务端回信的数据包（轮船，指定回信内容）
		SocketAddress address = packet.getSocketAddress();
		DatagramPacket returnPacket = new DatagramPacket(bytes, bytes.length, address);
		
		// 发送
		socket.send(returnPacket);
		
		System.out.println("发送了回信");
	}
	
	@Test
	public void testClient () throws IOException {
		// 准备要发送的数据
		String str = "你好啊";
		byte[] bytes = str.getBytes();
		
		// 准备要发送的目标地址,将域名解析为地址
		InetAddress address = InetAddress.getByName("127.0.0.1");
		// 创建要发送的数据包（轮船）,同时放入内容
		DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, 9000);
	
		// 创建客户端套接字（客户端码头）
		DatagramSocket socket = new DatagramSocket();
		// 发送
		socket.send(packet);
		
		// 接收回信
		byte[] buff = new byte[100];
		// 回信数据包（轮船）
		DatagramPacket returnPacket = new DatagramPacket(buff, buff.length);
		
		System.out.println("等待回信...");
		socket.receive(returnPacket);
		System.out.println("接收到回信了...");
		
		// 消息的实际长度
		int length = returnPacket.getLength();
		System.out.println(new String(buff, 0, length));
	}
}
