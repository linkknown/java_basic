package com.linkknown.exception;

import org.junit.jupiter.api.Test;

/**
 * �Զ����쳣����
 * @author Administrator
 *
 */
public class CustomExceptionTest {
	
	/**
	 *  �Զ����쳣����
	 * @throws UnderAgeException 
	 */
	@Test
	public void tsetCustomException () throws UnderAgeException {
		Person person = new Person();
		person.setName("����");
		person.setAge(20);
		person.work();
	
		Person person2 = new Person();
		person2.setName("����");
		person2.setAge(15);
		person2.work();
	}
}
