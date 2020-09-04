package com.linkknown.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CreateFutureTest {

	/**
	 * FutureTask + Callable 创建线程
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Integer> callable = new MyCallable();
		FutureTask<Integer> futureTask = new FutureTask<>(callable);
		// 子线程执行
		new Thread(futureTask).start();
		// 主线程获取子线程返回值
		Integer sum = futureTask.get();
		System.out.println("sum = " + sum);
	}
}

class MyCallable implements Callable<Integer> {

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