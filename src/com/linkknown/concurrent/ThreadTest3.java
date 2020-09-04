package com.linkknown.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class ThreadTest3 {

	static void sleep(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 线程池测试
	 */
	@Test
	public void testExecutors () {
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.execute(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		});
//		shutdownNow方法的解释是：线程池拒接收新提交的任务，同时立马关闭线程池，线程池里的任务不再执行。
//		shutdown方法的解释是：线程池拒接收新提交的任务，同时等待线程池里的任务执行完毕后关闭线程池。
		service.shutdown();
		
		ThreadTest3.sleep(10);
	}
	
	/**
	 * 线程池测试
	 */
	@Test
	public void testExecutors2 () {
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i=0; i<10; i++) {
			service.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
		
		service.shutdown();
		
		ThreadTest3.sleep(10);
	}
	
	/**
	 * 线程池测试
	 */
	@Test
	public void testExecutors3 () {
		// 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i=0; i<10; i++) {
			service.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
		
		service.shutdown();
		
		ThreadTest3.sleep(10);
	}
	
	public static void main(String[] args) {
		int corePoolSize = 1;		// 线程池长期维持的线程数，即使线程处于Idle状态，也不会回收
		int maximumPoolSize = 2;	// 线程数的上限
		long keepAliveTime = 1000;	// 超过corePoolSize的线程的idle时长，超过这个时间，多余的线程会被回收
		TimeUnit unit = TimeUnit.MILLISECONDS;	
		BlockingQueue<Runnable> workQueue = new SynchronousQueue<Runnable>();	//	 任务的排队队列
		ThreadFactory handler = Executors.defaultThreadFactory();				//  新线程的产生方式
		AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();			// 拒绝策略
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler, abortPolicy);
		for(int i=0;i<2;i++) {
			poolExecutor.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			});
		}   
		poolExecutor.shutdown();
	}
}
