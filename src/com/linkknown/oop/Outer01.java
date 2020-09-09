package com.linkknown.oop;

public class Outer01 {

	private String name = "�����ⲿ������";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * ��̬�ڲ���
	 * @author 38909
	 *
	 */
	public static class InnerClass {
		
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
	
	public void printName () {
		InnerClass innerClass = new InnerClass();
		innerClass.printName();
		
		System.out.println(this.name);
	}
}
