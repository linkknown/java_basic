package com.linkknown.concurrent;

public class CreateRunnableTest {

	/**
	 * ʹ�� static �����̲߳���
	 * һ���������ĸ��̣߳�ʹ�� Thread.currentThread().getName() ����ȡ�߳�����
	 * @param args
	 */
	public static void main(String[] args) {
		Runnable runnable = new MyRunnable();
		new Thread(runnable).start();
		new Thread(runnable).start();
		new Thread(runnable).start();
		
		System.out.println(Thread.currentThread().getName() + "�߳�ִ������~");
	}
}

class MyRunnable implements Runnable {

	@Override
	public void run() {
		for (int i=0; i<100; i++) {
			System.out.println(Thread.currentThread().getName() + " is running for " + i);
		}
		System.out.println(Thread.currentThread().getName() + "�߳�ִ������~");
	}
	
}
