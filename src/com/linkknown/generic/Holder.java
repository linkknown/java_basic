package com.linkknown.generic;

/**
 * T 类型持有类
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
		System.out.println("将 " + t.toString() + " 写入文件");
	}
}
