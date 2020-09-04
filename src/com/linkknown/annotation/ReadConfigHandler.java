package com.linkknown.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReadConfigHandler implements InvocationHandler {

	private Properties properties;

	public ReadConfigHandler(Properties properties) {
		super();
		this.properties = properties;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		ReadConfig readConfig = method.getDeclaredAnnotation(ReadConfig.class);
		if (readConfig == null) {
			return null;
		}
		String value = readConfig.value();
		String property = properties.getProperty(value);
		if (property == null || "".equals(property)) {
			return null;
		}
		Class<?> returnType = method.getReturnType();
		// java.lang.Class.isPrimitive() 确定指定的Class对象表示一个基本类型
		if (returnType.isPrimitive()) {
			if (returnType.equals(int.class)) {
				return Integer.valueOf(property);
			} else if (returnType.equals(long.class)) {
				return (Long.valueOf(property));
			} else if (returnType.equals(double.class)) {
				return (Double.valueOf(property));
			} else if (returnType.equals(float.class)) {
				return (Float.valueOf(property));
			} else if (returnType.equals(boolean.class)) {
				return (Boolean.valueOf(property));
			}
		} else {
			if (returnType.equals(Integer.class)) {
				return Integer.valueOf(property);
			} else if (returnType.equals(String.class)) {
				return String.valueOf(property);
			} else if (returnType.equals(Boolean.class)) {
				return Boolean.valueOf(property);
			}
		}
		return property;
	}

}
