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
		try {
			this.countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(String.format("%s ������ɻ������ϰ忪ʼ���...", this.name));
		}
		
	}

}
