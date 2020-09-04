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
			// ����һ������� socket
			serverSocket = new ServerSocket(9000);
			// ����һ���ͻ��� socket
			socket = serverSocket.accept();
			// �� socket �н�������
			inputStream = socket.getInputStream();
			byte[] bytes = new byte[100]; // ����Ϊ 1 ʱ�����ĻᲿ�ֶ�ȡ
			int len;
			while ((len = inputStream.read(bytes)) != -1) {
				System.out.print(new String(bytes, 0, len)); // ��ʱ��ӡ���Ļ�����, Ӧ�ø��� BufferedReader һ�ζ�ȡһ��
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
			// ���ӷ���� socket
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 9000);
			// �������ݵ������
			outputStream = socket.getOutputStream();
			outputStream.write("��ð� Java".getBytes());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			TcpTest.close(outputStream, socket);
		}
	}
}
