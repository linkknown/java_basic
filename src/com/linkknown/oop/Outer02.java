package com.linkknown.oop;

public class Outer02 {

	private String name = "�����ⲿ������";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * ʵ���ڲ���
	 * @author 38909
	 *
	 */
	public class InnerClass {
		
		private String name = "�����ڲ�������";

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
