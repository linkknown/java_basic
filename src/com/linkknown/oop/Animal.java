package com.linkknown.oop;

public class Animal {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		// ���� this.����
		this.name = name;
	}

	/**
	 * ˵��
	 */
	public void talk() {

	}

	/**
	 * ˯��
	 */
	public void sleep() {
		System.out.println("��ʼ˯����~");
	}

}
