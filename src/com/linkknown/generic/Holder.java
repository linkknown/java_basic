package com.linkknown.generic;

/**
 * T ���ͳ�����
 */
public class Holder<T> {

	private T t;
	
	public void setObject(T t) {
		this.t = t;
	}
	
	public void printObject () {
		System.out.println(t.toString());
	}
	
	public void writeToFile () {
		System.out.println("�� " + t.toString() + " д���ļ�");
	}
}
