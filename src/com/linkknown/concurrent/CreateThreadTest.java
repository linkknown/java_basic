package com.linkknown.concurrent;

public class CreateThreadTest {

	/**
	 * 使用 Thread 创建线程测试
	 * 一个创建了四个线程，使用 Thread.currentThread().getName() 来获取线程名称
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread1 = new MyThread();
		Thread thread2 = new MyThread();
		Thread thread3 = new MyThread();
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		System.out.println(Thread.currentThread().getName() + "线程执行完啦~");
	}
}

class MyThread extends Thread {
	@Override
	public void run() {
		for (int i=0; i<100; i++) {
			System.out.println(Thread.currentThread().getName() + " is running for " + i);
		}
		System.out.println(Thread.currentThread().getName() + "线程执行完啦~");
	}
}
