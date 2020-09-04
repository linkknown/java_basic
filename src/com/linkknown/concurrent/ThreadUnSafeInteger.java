package com.linkknown.concurrent;

/**
 * 
 * @author Administrator
 *
 */
public class ThreadUnSafeInteger {

	private int initNum;

	public ThreadUnSafeInteger(int initNum) {
		super();
		this.initNum = initNum;
	}
	
	public void add (int num) {
		this.initNum += num;
	}
	
	public void sub (int num) {
		this.initNum -= num;
	}
	
	public int get () {
		return this.initNum;
	}
	
}
