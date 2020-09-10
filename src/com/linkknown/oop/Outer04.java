package com.linkknown.oop;

public class Outer04 {

	private String name = "我是外部类属性";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void printName () {
		
		final int age = 10;
		
		InnerClassIterface innerClass = new InnerClassIterface() {
			
			private String name = "我是匿名内部类属性";
			
			@Override
			public void printName() {
				System.out.println(this.name);
				
				System.out.println(age);
				
				// 在匿名内部类中需要访问局部变量，那么这个局部变量必须用final修饰符修饰
//				age = 20;
//				System.out.println(age);
			}
		};
		
		innerClass.printName();
		System.out.println(this.name);
	}
}

interface InnerClassIterface {
	void printName();
}
