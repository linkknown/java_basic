package com.linkknown.socket;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

public class TcpTest {
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

	@Test
	public void testServer() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream inputStream = null;
		try {
			// 创建一个服务端 socket
			serverSocket = new ServerSocket(9000);
			// 接收一个客户端 socket
			socket = serverSocket.accept();
			// 从 socket 中接收数据
			inputStream = socket.getInputStream();
			byte[] bytes = new byte[100]; // 长度为 1 时，中文会部分读取
			int len;
			while ((len = inputStream.read(bytes)) != -1) {
				System.out.print(new String(bytes, 0, len)); // 此时打印中文会乱码, 应该改用 BufferedReader 一次读取一行
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			TcpTest.close(inputStream, socket, serverSocket);
		}
	}

	@Test
	public void testclient() {
		Socket socket = null;
		OutputStream outputStream = null;
		try {
			// 连接服务端 socket
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 9000);
			// 发送数据到服务端
			outputStream = socket.getOutputStream();
			outputStream.write("你好啊 Java".getBytes());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			TcpTest.close(outputStream, socket);
		}
	}
}
