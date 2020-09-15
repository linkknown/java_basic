package com.linkknown.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {

	/**
	 * ʹ�� Thread �����̲߳���
	 * һ���������ĸ��̣߳�ʹ�� Thread.currentThread().getName() ����ȡ�߳�����
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
		
		System.out.println(Thread.currentThread().getName() + "�߳�ִ������~");
	}
	
	
	/**
	 * ʹ�� Runnable �����̲߳���
	 * һ���������ĸ��̣߳�ʹ�� Thread.currentThread().getName() ����ȡ�߳�����
	 * @param args
	 */
//	public static void main(String[] args) {
	public static void testCreateRunnable () {
		Runnable runnable = new MyRunnable();
		new Thread(runnable).start();
		new Thread(runnable).start();
		new Thread(runnable).start();
		
		System.out.println(Thread.currentThread().getName() + "�߳�ִ������~");
	}
	
	
	/**
	 * FutureTask + Callable �����߳�
	 * 
	 * 1���쳣�ᱻ�׳�
	 * 2���ɽ����̷߳��ص����ݣ��첽�����
	 * 
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Integer> callable = new MyCallable();
		FutureTask<Integer> futureTask = new FutureTask<>(callable);
		// ���߳�ִ��
		new Thread(futureTask).start();
		// ���̻߳�ȡ���̷߳���ֵ
		Integer sum = futureTask.get();
		System.out.println("sum = " + sum);
	}
}
