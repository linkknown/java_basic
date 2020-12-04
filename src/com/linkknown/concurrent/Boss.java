package com.linkknown.concurrent;

import java.util.concurrent.CountDownLatch;

public class Boss implements Runnable {
	
	private String name;
	private CountDownLatch countDownLatch;

	public Boss(String name, CountDownLatch countDownLatch) {
		super();
		this.name = name;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		System.out.println(String.format("%s ���ڵȴ���������ɻ�...", this.name));
		long startTime =  System.currentTimeMillis();
		try {
			this.countDownLatch.await();
			
			System.out.println(String.format("%s�� ������ɻ������ϰ忪ʼ���...", this.name));
			long endTime =  System.currentTimeMillis();
			 System.out.println("�ϰ�һ������" + (endTime - startTime) + "ms");      
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
