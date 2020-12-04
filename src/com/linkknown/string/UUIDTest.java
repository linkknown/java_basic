package com.linkknown.string;

import java.util.UUID;

import org.junit.jupiter.api.Test;

/**
 * 唯一性 id 测试
 * @author Administrator
 *
 */
public class UUIDTest {

	@Test
	public void testUUID () {
		UUID uuid = UUID.randomUUID();
		String str = UUID.randomUUID().toString(); 		//用来生成数据库的主键id非常不错
		
		System.out.println(str);
	}
}
