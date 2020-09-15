package com.linkknown.concurrent;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i=0; i<100; i++) {
			sum += i;
			System.out.println(Thread.currentThread().getName() + " is running for " + i);
		}
		System.out.println(Thread.currentThread().getName() + "线程执行完啦~");
		return sum;
	}
	
}