package com.linkknown.generic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * ���Է��Ͳ���
 * 
 * @author Administrator
 *
 */
public class GenericEraseTest {

	/**
	 * ��֤���Ͳ���
	 */
	@Test
	public void testErase() {
		List<String> slst = new ArrayList<>();

		slst.add("hello");

		List<Integer> ilst = new ArrayList<>();
		ilst.add(10);

		// ���� true ��ʾ�����ڱ����ڱ�������
		System.out.println(slst.getClass() == ilst.getClass());
	}

	/**
	 * ͨ�����������������Ԫ�� ��֤���Ͳ���
	 * 
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testErase2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		ArrayList<String> lst = new ArrayList<>();
		lst.add("hello");

		lst.getClass().getMethod("add", Object.class).invoke(lst, 10);

		for (Object item : lst) {
			System.out.println(item);
		}
	}

	// ����һ���򵥵ķ��ͷ���
	public static <T> T random(T x, T y) {
		return y;
	}

	/**
	 * ���������ƶ�
	 */
	@Test
	public void test() {
		/** ��ָ�����͵�ʱ�� */
		Integer result1 = GenericEraseTest.random(1, 2); // ��������������Integer������TΪInteger����
		Number result2 = GenericEraseTest.random(1, 1.2); // ����������һ����Integer���Է����Float������ȡͬһ�������С����ΪNumber
		Object random = GenericEraseTest.random(1, "hello"); // ����������һ����Integer���Է����Float������ȡͬһ�������С����ΪObject

		/** ָ�����͵�ʱ�� */
		int a = GenericEraseTest.<Integer>random(1, 2); // ָ����Integer������ֻ��ΪInteger���ͻ���������
//		int b = GenericEraseTest.<Integer>random(1, 2.2); // �������ָ����Integer������ΪFloat
		Number c = GenericEraseTest.<Number>random(1, 2.2); // ָ��ΪNumber�����Կ���ΪInteger��Float
	}
}
