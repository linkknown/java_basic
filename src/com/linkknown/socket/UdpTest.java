package com.linkknown.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;

import org.junit.jupiter.api.Test;

/**
 * @author Administrator
 * UDP �������ݾͺ����ڴ����������, DatagramSocket ���������ͨ�Ÿۿڵ���ͷ, DatagramPacket �������������������ִ�
 * 
 * ���Ͷˣ�send��
 * 		����DatagramSocket,����˿ں�
 * 		����DatagramPacket,�ƶ����ݣ����ȣ���ַ�Ͷ˿�
 * 		ʹ��DatagramSocket����DatagramPacket
 * 		�ر�DatagramSocket
 * 		�쳣����
 * 
 * 
 * ���նˣ�receive��
 * 		����DatagramSocket,ƥ��˿ں�
 * 		����DatagramPacket�������������ݺ���Ч���ݳ���
 * 		����socket
 * 		����packet
 * 		�ر�DatagramSocket
 */
public class UdpTest {

	@Test
	public void testServer () throws IOException {

		// ����������׽��֣��������ͷ��,��Ҫ�ṩ�˿ں�,���ڵ�ǰ IP ���Զ��󶨶˿ں�
		DatagramSocket socket = new DatagramSocket(9000);
		
		byte[] buff = new byte[100];
		// ��������˽��յ����ݰ����ִ���ָ������������
		DatagramPacket packet = new DatagramPacket(buff, buff.length);
		
		System.out.println("��ʼ�ȴ���Ϣ...");
		
		// ����
		socket.receive(packet); 	// û�յ����ݾͻ�һֱ�����ȴ�
		
		System.out.println("���յ�����Ϣ...");
		
		// ��Ϣ��ʵ�ʳ���
		int length = packet.getLength();
		String str = new String(buff, 0, length);
		System.out.println("�յ�����Ϣ��" + str);
	
		// ���ͻ���
		String returnStr = "��л�������";
		byte[] bytes = returnStr.getBytes();
		
		// ��������˻��ŵ����ݰ����ִ���ָ���������ݣ�
		SocketAddress address = packet.getSocketAddress();
		DatagramPacket returnPacket = new DatagramPacket(bytes, bytes.length, address);
		
		// ����
		socket.send(returnPacket);
		
		System.out.println("�����˻���");
	}
	
	@Test
	public void testClient () throws IOException {
		// ׼��Ҫ���͵�����
		String str = "��ð�";
		byte[] bytes = str.getBytes();
		
		// ׼��Ҫ���͵�Ŀ���ַ,����������Ϊ��ַ
		InetAddress address = InetAddress.getByName("127.0.0.1");
		// ����Ҫ���͵����ݰ����ִ���,ͬʱ��������
		DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, 9000);
	
		// �����ͻ����׽��֣��ͻ�����ͷ��
		DatagramSocket socket = new DatagramSocket();
		// ����
		socket.send(packet);
		
		// ���ջ���
		byte[] buff = new byte[100];
		// �������ݰ����ִ���
		DatagramPacket returnPacket = new DatagramPacket(buff, buff.length);
		
		System.out.println("�ȴ�����...");
		socket.receive(returnPacket);
		System.out.println("���յ�������...");
		
		// ��Ϣ��ʵ�ʳ���
		int length = returnPacket.getLength();
		System.out.println(new String(buff, 0, length));
	}
}
