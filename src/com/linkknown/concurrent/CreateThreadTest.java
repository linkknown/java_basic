package com.linkknown.concurrent;

public class CreateThreadTest {

	/**
	 * ʹ�� Thread �����̲߳���
	 * һ���������ĸ��̣߳�ʹ�� Thread.currentThread().getName() ����ȡ�߳�����
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread1 = new MyThread();
		Thread thread2 = new MyThread();
		Thread thread3 = new MyThread();
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		System.out.println(Thread.currentThread().getName() + "�߳�ִ������~");
	}
}

class MyThread extends Thread {
	@Override
	public void run() {
		for (int i=0; i<100; i++) {
			System.out.println(Thread.currentThread().getName() + " is running for " + i);
		}
		System.out.println(Thread.currentThread().getName() + "�߳�ִ������~");
	}
}
