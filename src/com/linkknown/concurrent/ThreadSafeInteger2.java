package com.linkknown.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * @author Administrator
 *         ReentrantLock的作用和synchronize是一样的，都是实现锁的功能，但是ReentrantLock需要手写代码对锁进行获取和释放(一定要在finally里面释放)，
 *         要不然就永远死锁了，ReentrantLock也可以用来做线程之间的挂起和通知，synchronize一般是使用object的wait和notify来实现，
 *         ReentrantLock使用Condition来实现线程之间的通信。
 *         ReentrantReadWriteLock锁是一个读写分离的锁，这种锁主要用于读多写少的业务场景，口诀就是：读读共享、写写互斥、读写互斥。
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
				System.out.println(Thread.currentThread().getName() + "正在读取...");
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
