package com.linkknown.exception;

public class Person {

	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void work () throws UnderAgeException {
		if (age < 18) {
			throw new UnderAgeException(String.format("%s 年龄 %d，不满足参加工作条件！", this.name, this.age));
		}
		System.out.println(this.name + "开始工作拿钱啦！");
	}
	
}
