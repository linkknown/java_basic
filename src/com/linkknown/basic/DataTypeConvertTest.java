package com.linkknown.basic;

import org.junit.jupiter.api.Test;

public class DataTypeConvertTest {

	@Test
	public void testConvert () {
		// 测试自动类型转换
		byte a = 100;
		int a1 = a;
		
		// 测试强制类型转换
		byte b = (byte)128;
		
		// 测试隐含强制类型转换
		byte c = 3;
	}
}
