package com.linkknown.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * @author Administrator
 * ReentrantLock�����ú�synchronize��һ���ģ�����ʵ�����Ĺ��ܣ�����ReentrantLock��Ҫ��д����������л�ȡ���ͷ�(һ��Ҫ��finally�����ͷ�)��
 * Ҫ��Ȼ����Զ�����ˣ�ReentrantLockҲ�����������߳�֮��Ĺ����֪ͨ��synchronizeһ����ʹ��object��wait��notify��ʵ�֣�
 * ReentrantLockʹ��Condition��ʵ���߳�֮���ͨ�š�
 * ReentrantReadWriteLock����һ����д�����������������Ҫ���ڶ���д�ٵ�ҵ�񳡾����ھ����ǣ���������лл���⡢��д���⡣
 * 
 */
public class ThreadSafeInteger2 {

	private int initNum;
	
	public ThreadSafeInteger2(int initNum) {
		super();
		this.initNum = initNum;
	}
	
//	ReentrantLock Ĭ�������Ϊ����ƽ��
//
//	����ƽ���빫ƽ��������
//	��ƽ����£���������һ���Ӱ�˳��ִ�У�����ִ֤��˳�򡣣������ĸ����ʱ�����Ŷӣ�
//	����ƽ����£�������״̬�����ӣ�jvm���Զ�������δ�������������Ȳ�ӡ������������˳������ٶȻ���죩
	private static ReentrantLock reentrantLock = new ReentrantLock();			// ����Ĭ��Ϊ false,����ƽ��
	private static ReentrantLock reentrantLock2 = new ReentrantLock(true);		// ��ƽ��
	
	private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
	private static final Lock readLock = reentrantReadWriteLock.readLock();
	private static final Lock writeLock = reentrantReadWriteLock.writeLock();

	
	public void add (int num) {
		try {
			reentrantLock.lock();
			this.initNum += num;
		} finally {
			reentrantLock.unlock();
		}
	}
	
	public void add2 (int num) {
		try {
			reentrantLock2.lock();
			this.initNum += num;
		} finally {
			reentrantLock2.unlock();
		}
	}
	
	public void add3 (int num) {
		try {
			writeLock.lock();
			this.initNum += num;
		} finally {
			writeLock.unlock();
		}
	}
	
	public void sub (int num) {
		try {
			reentrantLock.lock();
			this.initNum -= num;
		} finally {
			reentrantLock.unlock();
		}
	}
	
	public void sub2 (int num) {
		try {
			reentrantLock2.lock();
			this.initNum -= num;
		} finally {
			reentrantLock2.unlock();
		}
	}
	
	public void sub3 (int num) {
		try {
			writeLock.lock();
			this.initNum -= num;
		} finally {
			writeLock.unlock();
		}
	}
	
	public  int get () {
		try {
			reentrantLock.lock();
			
			for (int i=0; i<10; i++) {
				System.out.println(Thread.currentThread().getName() + "���ڶ�ȡ...");
			}
			
		} finally {
			reentrantLock.unlock();
		}
		return this.initNum;
	}
	
	public int get2 () {
		try {
			reentrantLock2.lock();
			
			for (int i=0; i<10; i++) {
				System.out.println(Thread.currentThread().getName() + "���ڶ�ȡ...");
			}
			
		} finally {
			reentrantLock2.unlock();
		}
		return this.initNum;
	}
	
	public int get3 () {
		try {
			readLock.lock();
			
			for (int i=0; i<10; i++) {
				System.out.println(Thread.currentThread().getName() + "���ڶ�ȡ...");
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			return this.initNum;
		} finally {
			readLock.unlock();
		}
		
	}
}
