package com.linkknown.concurrent;

import java.util.Random;
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
	
	private static void testThreadPool() {
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
	
	
	/**
	 * �����û��߳�
	 * ���⣺��ש�Ĳ���˵���ɾͲ��ɣ��Ƿ��Ŀɸɿɲ��ɣ��Ƿ��Ĳ��ɵ�ʱ�򣬰�ש��ҲҪ��Ϣ���������⣬�޷�֪ͨ��
	 */
	public static void testUserThread () {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (;;) {
					System.out.println("���ǰ�ש�ģ��Ҳ�����������Ϣ#######");
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread t2  = new Thread(new Runnable() {
			@Override
			public void run() {
				for (;;) {
					System.out.println("���ǸǷ��ģ�����̫���Ҿ�Ҫ��Ϣ~~~~~~");
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if (new Random().nextInt(100) > 90) {
						System.out.println("��������̫�ȣ��Ҳ��Ƿ���~~~~~~");
						break;
					}
				}
			}
		});
		t1.start();
		t2.start();
	}
	
	/**
	 * �����ػ��߳�
	 * ��������⣺����Ҫ�ȸǷ���֪ͨ��ֻҪ�����Ƿ��Ķ���Ϣ�ˣ���ש���Զ���Ϣ
	 */
	public static void testDaemonThread () {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (;;) {
					System.out.println("���ǰ�ש�ģ��Ҳ�����������Ϣ#######");
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread t2  = new Thread(new Runnable() {
			@Override
			public void run() {
				for (;;) {
					System.out.println("���ǸǷ��ģ�����̫���Ҿ�Ҫ��Ϣ~~~~~~");
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if (new Random().nextInt(100) > 90) {
						System.out.println("��������̫�ȣ��Ҳ��Ƿ���~~~~~~");
						break;
					}
				}
			}
		});
		// ����Ϊ�ػ��߳�
		t1.setDaemon(true);
		t1.start();
		t2.start();
	}
	
	public static void main(String[] args) {
		// �����̳߳�
//		testThreadPool();
		
		// �����û��߳�
//		testUserThread();
		
		// �����ػ��߳�
		testDaemonThread();
	}

}
