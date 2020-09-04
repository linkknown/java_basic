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
		System.out.println(String.format("%s 正在等待所有人完成活...", this.name));
		try {
			this.countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(String.format("%s 工人完成活啦，老板开始检查...", this.name));
		}
		
	}

}
