package com.linkknown.generic;

/**
 * 描述类信息
 */
public class ClassUtil {
	
	// 泛型方法
	public <T> void getClasName (T t) {
		System.out.println(t.getClass().getName());
	}
	// 泛型方法
	public <T> T getInstance (T t) throws InstantiationException, IllegalAccessException {
		return (T) t.getClass().newInstance();
	}
	// 泛型方法
	public static <T> void getClasName2 (T t) {
		System.out.println(t.getClass().getName());
	}
	// 泛型方法
	public static <T> T getInstance2 (T t) throws InstantiationException, IllegalAccessException {
		return (T) t.getClass().newInstance();
	}
}
