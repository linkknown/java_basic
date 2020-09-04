package com.linkknown.concurrent;

public class CreateRunnableTest {

	/**
	 * 使用 static 创建线程测试
	 * 一个创建了四个线程，使用 Thread.currentThread().getName() 来获取线程名称
	 * @param args
	 */
	public static void main(String[] args) {
		Runnable runnable = new MyRunnable();
		new Thread(runnable).start();
		new Thread(runnable).start();
		new Thread(runnable).start();
		
		System.out.println(Thread.currentThread().getName() + "线程执行完啦~");
	}
}

class MyRunnable implements Runnable {

	@Override
	public void run() {
		for (int i=0; i<100; i++) {
			System.out.println(Thread.currentThread().getName() + " is running for " + i);
		}
		System.out.println(Thread.currentThread().getName() + "线程执行完啦~");
	}
	
}
