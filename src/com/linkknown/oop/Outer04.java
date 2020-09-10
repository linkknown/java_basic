package com.linkknown.oop;

public class Outer04 {

	private String name = "�����ⲿ������";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void printName () {
		
		final int age = 10;
		
		InnerClassIterface innerClass = new InnerClassIterface() {
			
			private String name = "���������ڲ�������";
			
			@Override
			public void printName() {
				System.out.println(this.name);
				
				System.out.println(age);
				
				// �������ڲ�������Ҫ���ʾֲ���������ô����ֲ�����������final���η�����
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
