package com.linkknown.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * @author Administrator
 * ReentrantLock的作用和synchronize是一样的，都是实现锁的功能，但是ReentrantLock需要手写代码对锁进行获取和释放(一定要在finally里面释放)，
 * 要不然就永远死锁了，ReentrantLock也可以用来做线程之间的挂起和通知，synchronize一般是使用object的wait和notify来实现，
 * ReentrantLock使用Condition来实现线程之间的通信。
 * ReentrantReadWriteLock锁是一个读写分离的锁，这种锁主要用于读多写少的业务场景，口诀就是：读读共享、谢谢互斥、读写互斥。
 * 
 */
public class ThreadSafeInteger2 {

	private int initNum;
	
	public ThreadSafeInteger2(int initNum) {
		super();
		this.initNum = initNum;
	}
	
//	ReentrantLock 默认情况下为不公平锁
//
//	不公平锁与公平锁的区别：
//	公平情况下，操作会排一个队按顺序执行，来保证执行顺序。（会消耗更多的时间来排队）
//	不公平情况下，是无序状态允许插队，jvm会自动计算如何处理更快速来调度插队。（如果不关心顺序，这个速度会更快）
	private static ReentrantLock reentrantLock = new ReentrantLock();			// 参数默认为 false,不公平锁
	private static ReentrantLock reentrantLock2 = new ReentrantLock(true);		// 公平锁
	
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
				System.out.println(Thread.currentThread().getName() + "正在读取...");
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
				System.out.println(Thread.currentThread().getName() + "正在读取...");
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
