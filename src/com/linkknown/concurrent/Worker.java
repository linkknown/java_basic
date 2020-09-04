package com.linkknown.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {

	private String name;
	private CountDownLatch countDownLatch;

	public Worker(String name, CountDownLatch countDownLatch) {
		super();
		this.name = name;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		this.startWork();
		try {
			TimeUnit.SECONDS.sleep(new Random().nextInt(10));
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(String.format("%s 工作出问题啦~", this.name));
		} finally {
			this.endWork();
			// 计算减 1,标志线程执行完成
			this.countDownLatch.countDown();
		}
	}

	private void endWork() {
		System.out.println(String.format("%s 完成工作啦~", this.name));
	}

	private void startWork() {
		System.out.println(String.format("%s 开始工作啦~", this.name));
	}

}
