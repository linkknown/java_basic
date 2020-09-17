package com.linkknown.generic;

/**
 * √Ë ˆ¿‡–≈œ¢
 * @author Administrator
 *
 */
public class ClassUtil {

	public <T> void getClasName (T t) {
		System.out.println(t.getClass().getName());
	}
	
	public <T> T getInstance (T t) throws InstantiationException, IllegalAccessException {
		return (T) t.getClass().newInstance();
	}
	
	public static <T> void getClasName2 (T t) {
		System.out.println(t.getClass().getName());
	}

	public static <T> T getInstance2 (T t) throws InstantiationException, IllegalAccessException {
		return (T) t.getClass().newInstance();
	}
}
