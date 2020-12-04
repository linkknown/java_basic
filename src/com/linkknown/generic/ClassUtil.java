package com.linkknown.generic;

/**
 * ��������Ϣ
 */
public class ClassUtil {
	
	// ���ͷ���
	public <T> void getClasName (T t) {
		System.out.println(t.getClass().getName());
	}
	// ���ͷ���
	public <T> T getInstance (T t) throws InstantiationException, IllegalAccessException {
		return (T) t.getClass().newInstance();
	}
	// ���ͷ���
	public static <T> void getClasName2 (T t) {
		System.out.println(t.getClass().getName());
	}
	// ���ͷ���
	public static <T> T getInstance2 (T t) throws InstantiationException, IllegalAccessException {
		return (T) t.getClass().newInstance();
	}
}
