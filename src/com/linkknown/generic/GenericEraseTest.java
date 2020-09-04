package com.linkknown.generic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 测试泛型擦除
 * 
 * @author Administrator
 *
 */
public class GenericEraseTest {

	/**
	 * 验证泛型擦除
	 */
	@Test
	public void testErase() {
		List<String> slst = new ArrayList<>();

		slst.add("hello");

		List<Integer> ilst = new ArrayList<>();
		ilst.add(10);

		// 返回 true 表示类型在编译期被擦除了
		System.out.println(slst.getClass() == ilst.getClass());
	}

	/**
	 * 通过反射添加其它类型元素 验证泛型擦除
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

	// 这是一个简单的泛型方法
	public static <T> T random(T x, T y) {
		return y;
	}

	/**
	 * 泛型类型推断
	 */
	@Test
	public void test() {
		/** 不指定泛型的时候 */
		Integer result1 = GenericEraseTest.random(1, 2); // 这两个参数都是Integer，所以T为Integer类型
		Number result2 = GenericEraseTest.random(1, 1.2); // 这两个参数一个是Integer，以风格是Float，所以取同一父类的最小级，为Number
		Object random = GenericEraseTest.random(1, "hello"); // 这两个参数一个是Integer，以风格是Float，所以取同一父类的最小级，为Object

		/** 指定泛型的时候 */
		int a = GenericEraseTest.<Integer>random(1, 2); // 指定了Integer，所以只能为Integer类型或者其子类
//		int b = GenericEraseTest.<Integer>random(1, 2.2); // 编译错误，指定了Integer，不能为Float
		Number c = GenericEraseTest.<Number>random(1, 2.2); // 指定为Number，所以可以为Integer和Float
	}
}
