package com.linkknown.oop;

public abstract class Person {

	private String name;
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	/**
	 * 工作
	 */
	public abstract void work();
	
	public void eat () {
		System.out.println("开始吃饭啦~");
	}

}
