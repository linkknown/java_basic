package com.linkknown.annotation;

import java.util.ArrayList;
import java.util.List;

public class Student implements Person {

	private String name;
	
	@Override
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Deprecated
	public void showStudentInfo () {
		System.out.println(this);
	}
	
	public void test () {
		// ºöÂÔ¾¯¸æ
		@SuppressWarnings("rawtypes")
		List lst = new ArrayList();
	}

}
