package com.linkknown.oop;

public class Animal {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		// 调用 this.属性
		this.name = name;
	}

	/**
	 * 说话
	 */
	public void talk() {

	}

	/**
	 * 睡觉
	 */
	public void sleep() {
		System.out.println("开始睡觉啦~");
	}

}
