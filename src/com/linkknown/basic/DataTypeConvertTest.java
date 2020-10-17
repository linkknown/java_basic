package com.linkknown.basic;

import org.junit.jupiter.api.Test;

public class DataTypeConvertTest {

	@Test
	public void testConvert () {
		// 测试自动类型转换
		byte a = 100;
		int a1 = a;
		
		// 自动类型转换，char 和 int 互转
		int b = 'a';
		System.out.println(b);
		char c = 97;
		System.out.println(c);
		
		
		// 测试强制类型转换
		byte d = (byte)128;
		
		
		// 测试隐含强制类型转换
		byte e = 3;
	}
}
