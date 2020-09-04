package com.linkknown.string;

import org.junit.jupiter.api.Test;

/**
 * 不可变字符串测试类
 * @author Administrator
 *
 */
public class ImmutableStringTest {

	
	@Test
	public void testImmutableString () {
		String str = "helloworld";
		System.out.println(str);
		String upperStr = str.toUpperCase();
	
		System.out.println(upperStr);
		System.out.println(str);		// 调用前后 str 值不变,证明 toUpperCase 是返回新字符串
	}
	
	@Test
	public void testImmutableString2 () {
		String str = "helloworld";
		System.out.println(str);
		String upperStr = str.substring(0, 5);
	
		System.out.println(upperStr);
		System.out.println(str);		// 调用前后 str 值不变,证明 substring 是返回新字符串
	}
}
