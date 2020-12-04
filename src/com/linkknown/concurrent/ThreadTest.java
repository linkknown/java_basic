package com.linkknown.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		// �������߳�
//		testMain();
//		
//		// �����̴߳�����ʽ 1
//		testCreateThread();
//		
//		// �����̴߳�����ʽ 2
//		testCreateRunnable();
		
		// �����̴߳�����ʽ 3
//		testCreateCallable();
		
		// ��ʱ����
		testCreateCallableWithTimeout();
	}
	
	// �������߳�
	public static void testMain () {
		System.out.println(Thread.currentThread().getName());
	}
	
	
	/**
	 * ʹ�� Thread �����̲߳���
	 * һ���������ĸ��̣߳�ʹ�� Thread.currentThread().getName() ����ȡ�߳�����
	 * @param args
	 */
	public static void testCreateThread () {
		Thread thread1 = new MyThread();
		Thread thread2 = new MyThread();
		Thread thread3 = new MyThread();
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		System.out.println(Thread.currentThread().getName() + "�߳�ִ������~");
	}
	
	
	/**
	 * ʹ�� Runnable �����̲߳���
	 * һ���������ĸ��̣߳�ʹ�� Thread.currentThread().getName() ����ȡ�߳�����
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
		
		System.out.println(Thread.currentThread().getName() + "�߳�ִ������~");
	}
	
	
	/**
	 * FutureTask + Callable �����߳�
	 * 
	 * 1���쳣�ᱻ�׳�
	 * 2���ɽ����̷߳��ص����ݣ��첽�����
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void testCreateCallable () throws InterruptedException, ExecutionException {
		Callable<Integer> callable = new MyCallable();
		FutureTask<Integer> futureTask = new FutureTask<>(callable);
		// ���߳�ִ��
		new Thread(futureTask).start();
		
		System.out.println("helloworld");
		System.out.println("helloworld");
		System.out.println("helloworld");
		
		// ���̻߳�ȡ���̷߳���ֵ
		Integer sum = futureTask.get();
		System.out.println("sum = " + sum);
	}
	
	
	/**
	 * ����ʱʱ����첽����
	 */
	public static void testCreateCallableWithTimeout () {
		Callable<Integer> callable = new MyCallable();
		FutureTask<Integer> futureTask = new FutureTask<>(callable);
		// ���߳�ִ��
		new Thread(futureTask).start();
		
		System.out.println("helloworld");
		System.out.println("helloworld");
		System.out.println("helloworld");
		
		// ���̻߳�ȡ���̷߳���ֵ
		try {
			Integer sum = futureTask.get(1, TimeUnit.SECONDS);		// ���ó�ʱʱ��
			
			System.out.println("sum = " + sum);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.out.println("ִ�г�ʱ��~~");
		} finally {
			futureTask.cancel(true);								// ȡ������
		}
	}
}
