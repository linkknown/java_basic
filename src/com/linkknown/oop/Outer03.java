package com.linkknown.oop;

import com.linkknown.oop.Outer02.InnerClass;

public class Outer03 {

	private String name = "我是外部类属性";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public void printName () {
		class InnerClass {
			
			private String name = "我是内部类属性";

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
