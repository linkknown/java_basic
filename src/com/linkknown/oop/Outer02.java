package com.linkknown.oop;

public class Outer02 {

	private String name = "我是外部类属性";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 实例内部类
	 * @author 38909
	 *
	 */
	public class InnerClass {
		
		private String name = "我是内部类属性";

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
		public void printName () {
			System.out.println(this.name);
			
			System.out.println(Outer02.this.name);
		}
		
	}
	
	public void printName () {
		InnerClass innerClass = new InnerClass();
		innerClass.printName();
	}
}
