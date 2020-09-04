package com.linkknown.exception;

import org.junit.jupiter.api.Test;

/**
 * 自定义异常测试
 * @author Administrator
 *
 */
public class CustomExceptionTest {
	
	/**
	 *  自定义异常测试
	 * @throws UnderAgeException 
	 */
	@Test
	public void tsetCustomException () throws UnderAgeException {
		Person person = new Person();
		person.setName("张三");
		person.setAge(20);
		person.work();
	
		Person person2 = new Person();
		person2.setName("李四");
		person2.setAge(15);
		person2.work();
	}
}
