package com.linkknown.oop;

import com.linkknown.oop.Outer02.InnerClass;

public class Outer03 {

	private String name = "�����ⲿ������";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public void printName () {
		class InnerClass {
			
			private String name = "�����ڲ�������";

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}
			
			
			public void printName () {
				System.out.println(this.name);
			}
			
		}
		
		InnerClass innerClass = new InnerClass();
		innerClass.printName();
		System.out.println(this.name);
	}
}
