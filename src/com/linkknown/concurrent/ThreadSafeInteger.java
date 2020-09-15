package com.linkknown.concurrent;

/**
 * 
 * @author Administrator
 *
 */
public class ThreadSafeInteger {

	private int initNum;
//	private Object object = new Object();

	public ThreadSafeInteger(int initNum) {
		super();
		this.initNum = initNum;
	}
	
	public synchronized void add (int num) {
		this.initNum += num;
	}
	
	public void add2 (int num) {
		synchronized (this) {			// this ¿É¸Ä³É object
			this.initNum += num;
		}
	}
	
	public synchronized void sub (int num) {
		this.initNum -= num;
	}
	
	public void sub2 (int num) {
		synchronized (this) {
			this.initNum -= num;
		}
	}
	
	public synchronized int get () {
		return this.initNum;
	}
}
