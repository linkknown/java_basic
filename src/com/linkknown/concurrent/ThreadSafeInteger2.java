package com.linkknown.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * @author Administrator
 *         ReentrantLock�����ú�synchronize��һ���ģ�����ʵ�����Ĺ��ܣ�����ReentrantLock��Ҫ��д����������л�ȡ���ͷ�(һ��Ҫ��finally�����ͷ�)��
 *         Ҫ��Ȼ����Զ�����ˣ�ReentrantLockҲ�����������߳�֮��Ĺ����֪ͨ��synchronizeһ����ʹ��object��wait��notify��ʵ�֣�
 *         ReentrantLockʹ��Condition��ʵ���߳�֮���ͨ�š�
 *         ReentrantReadWriteLock����һ����д�����������������Ҫ���ڶ���д�ٵ�ҵ�񳡾����ھ����ǣ���������дд���⡢��д���⡣
 */
public class ThreadSafeInteger2 {

	private int initNum;

	public ThreadSafeInteger2(int initNum) {
		super();
		this.initNum = initNum;
	}

	private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
	private Lock readLock = reentrantReadWriteLock.readLock();
	private Lock writeLock = reentrantReadWriteLock.writeLock();

	public void add(int num) {
		try {
			writeLock.lock();
			this.initNum += num;
		} finally {
			writeLock.unlock();
		}
	}

	public void sub(int num) {
		try {
			writeLock.lock();
			this.initNum -= num;
		} finally {
			writeLock.unlock();
		}
	}

	public int get() {
		try {
			readLock.lock();

			for (int i = 0; i < 10; i++) {
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
