package com.linkknown.exception;

/**
 * 自定义异常类,描述未成年异常
 * @author Administrator
 *
 */
public class UnderAgeException extends Exception {


	public UnderAgeException(String msg) {
		super(msg);
	}
	
	public UnderAgeException() {
		super();
	}
}
