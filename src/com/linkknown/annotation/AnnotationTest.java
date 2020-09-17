package com.linkknown.annotation;

import org.junit.jupiter.api.Test;

import com.linkknown.annotation.ConfigUtil.Config;

public class AnnotationTest {

	@Test
	public void testAnnotation () {
		Student person = new Student();
		person.showStudentInfo();
	}
	
	/**
	 * �Զ���ע�����
	 */
	@Test
	public void testAnnotation2 () {
		Config config = ConfigUtil.getInstance();
		System.out.println(config.getUserName());
		System.out.println(config.getPassWord());
	}
	
}
