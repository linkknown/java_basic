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
	 * �̳߳ز���
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
//		shutdownNow�����Ľ����ǣ��̳߳ؾܽ������ύ������ͬʱ����ر��̳߳أ��̳߳����������ִ�С�
//		shutdown�����Ľ����ǣ��̳߳ؾܽ������ύ������ͬʱ�ȴ��̳߳��������ִ����Ϻ�ر��̳߳ء�
		service.shutdown();
		
		ThreadTest3.sleep(10);
	}
	
	/**
	 * �̳߳ز���
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
	 * �̳߳ز���
	 */
	@Test
	public void testExecutors3 () {
		// ����һ���ɻ����̳߳أ�����̳߳س��ȳ���������Ҫ���������տ����̣߳����޿ɻ��գ����½��̡߳�
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
		int corePoolSize = 1;		// �̳߳س���ά�ֵ��߳�������ʹ�̴߳���Idle״̬��Ҳ�������
		int maximumPoolSize = 2;	// �߳���������
		long keepAliveTime = 1000;	// ����corePoolSize���̵߳�idleʱ�����������ʱ�䣬������̻߳ᱻ����
		TimeUnit unit = TimeUnit.MILLISECONDS;	
		BlockingQueue<Runnable> workQueue = new SynchronousQueue<Runnable>();	//	 ������ŶӶ���
		ThreadFactory handler = Executors.defaultThreadFactory();				//  ���̵߳Ĳ�����ʽ
		AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();			// �ܾ�����
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
