package com.linkknown.generic;

/**
 * T ���ͳ�����
 * @author Administrator
 *
 * @param <T>
 */
public class Holder<T> {

	private T t;
	
	public void setObject(T t) {
		this.t = t;
	}
	
	public void printObject () {
		System.out.println(t.toString());
	}
	
	public void wirteToFile () {
		System.out.println("�� " + t.toString() + " д���ļ�");
	}
}
