package com.linkknown.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyCallable implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i=0; i<100; i++) {
			sum += i;
			System.out.println(Thread.currentThread().getName() + " is running for " + i);
			
			TimeUnit.MILLISECONDS.sleep(100);
		}
		System.out.println(Thread.currentThread().getName() + "�߳�ִ������~");
		return sum;
	}
	
}