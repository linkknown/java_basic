package com.linkknown.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogInterceptor implements InvocationHandler {

	/**
	 * 被代理的对象
	 */
	private Object delegate;

	public LogInterceptor(Object delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before invoke method: " + method.getName());
		Object result = method.invoke(this.delegate, args);
		System.out.println("after invoke method: " + method.getName());
		return result;
	}

}
