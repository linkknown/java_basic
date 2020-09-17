package com.linkknown.annotation;

import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReadConfigHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		InputStream inputStream = ReadConfigHandler.class.getClassLoader().getResourceAsStream("com/linkknown/annotation/config.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		
		ReadConfig readConfig = method.getDeclaredAnnotation(ReadConfig.class);
		if (readConfig == null) {
			return null;
		}
		
		String value = readConfig.value();
		String property = properties.getProperty(value);
		if (property == null || "".equals(property.trim())) {
			return null;
		}
		Class<?> returnType = method.getReturnType();
		if (returnType.isPrimitive()) {
			if (returnType.equals(int.class)) {
				return Integer.valueOf(property);
			} else if (returnType.equals(long.class)) {
				return Long.valueOf(property);
			} else if (returnType.equals(double.class)) {
				return Double.valueOf(property);
			} else if (returnType.equals(float.class)) {
				return Float.valueOf(property);
			} else if (returnType.equals(boolean.class)) {
				return Boolean.valueOf(property);
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
