package com.linkknown.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {

	/**
	 * 使用 Thread 创建线程测试
	 * 一个创建了四个线程，使用 Thread.currentThread().getName() 来获取线程名称
	 * @param args
	 */
//	public static void main(String[] args) {
	public static void testCreateThread () {
		Thread thread1 = new MyThread();
		Thread thread2 = new MyThread();
		Thread thread3 = new MyThread();
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		System.out.println(Thread.currentThread().getName() + "线程执行完啦~");
	}
	
	
	/**
	 * 使用 Runnable 创建线程测试
	 * 一共创建了四个线程，使用 Thread.currentThread().getName() 来获取线程名称
	 * @param args
	 */
//	public static void main(String[] args) {
	public static void testCreateRunnable () {
		Runnable runnable = new MyRunnable();
		new Thread(runnable).start();
		new Thread(runnable).start();
		new Thread(runnable).start();
		
		System.out.println(Thread.currentThread().getName() + "线程执行完啦~");
	}
	
	
	/**
	 * FutureTask + Callable 创建线程
	 * 
	 * 1、异常会被抛出
	 * 2、可接收线程返回的数据（异步结果）
	 * 
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
