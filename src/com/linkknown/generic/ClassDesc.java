package com.linkknown.generic;

/**
 * ��������Ϣ
 * @author Administrator
 *
 */
public class ClassDesc {

	public <T> void getClassName (T t) {
		System.out.println(t.getClass().getName());
	}
	
	public static <T> void getClassName2 (T t) {
		System.out.println(t.getClass().getName());
	}
}
