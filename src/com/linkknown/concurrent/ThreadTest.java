package com.linkknown.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		// 测试主线程
//		testMain();
//		
//		// 测试线程创建方式 1
//		testCreateThread();
//		
//		// 测试线程创建方式 2
//		testCreateRunnable();
		
		// 测试线程创建方式 3
//		testCreateCallable();
		
		// 超时设置
		testCreateCallableWithTimeout();
	}
	
	// 测试主线程
	public static void testMain () {
		System.out.println(Thread.currentThread().getName());
	}
	
	
	/**
	 * 使用 Thread 创建线程测试
	 * 一个创建了四个线程，使用 Thread.currentThread().getName() 来获取线程名称
	 * @param args
	 */
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
	public static void testCreateRunnable () {
		Runnable runnable = new MyRunnable();
		
		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(runnable);
		Thread thread3 = new Thread(runnable);
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		System.out.println(Thread.currentThread().getName() + "线程执行完啦~");
	}
	
	
	/**
	 * FutureTask + Callable 创建线程
	 * 
	 * 1、异常会被抛出
	 * 2、可接收线程返回的数据（异步结果）
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void testCreateCallable () throws InterruptedException, ExecutionException {
		Callable<Integer> callable = new MyCallable();
		FutureTask<Integer> futureTask = new FutureTask<>(callable);
		// 子线程执行
		new Thread(futureTask).start();
		
		System.out.println("helloworld");
		System.out.println("helloworld");
		System.out.println("helloworld");
		
		// 主线程获取子线程返回值
		Integer sum = futureTask.get();
		System.out.println("sum = " + sum);
	}
	
	
	/**
	 * 带超时时间的异步任务
	 */
	public static void testCreateCallableWithTimeout () {
		Callable<Integer> callable = new MyCallable();
		FutureTask<Integer> futureTask = new FutureTask<>(callable);
		// 子线程执行
		new Thread(futureTask).start();
		
		System.out.println("helloworld");
		System.out.println("helloworld");
		System.out.println("helloworld");
		
		// 主线程获取子线程返回值
		try {
			Integer sum = futureTask.get(1, TimeUnit.SECONDS);		// 设置超时时间
			
			System.out.println("sum = " + sum);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.out.println("执行超时啦~~");
		} finally {
			futureTask.cancel(true);								// 取消任务
		}
	}
}
