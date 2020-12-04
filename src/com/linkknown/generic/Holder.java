package com.linkknown.generic;

/**
 * T 类型持有类
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
		System.out.println("将 " + t.toString() + " 写入文件");
	}
}
