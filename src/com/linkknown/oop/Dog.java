package com.linkknown.oop;

public class Dog extends Animal {
	
	/**
	 * 玩伴
	 */
	private Dog playmate;
	
	@Override
	public void talk() {
		System.out.println("汪汪汪...");
	}
	
	/**
	 * 重复的叫
	 * @param repeatcount
	 */
	public void talk (int repeatcount) {
		for (int i=0; i< repeatcount; i++) {
			this.talk();
		}
	}

	public Dog getPlaymate() {
		return playmate;
	}

	public void setPlaymate(Dog playmate) {
		this.playmate = playmate;
	}
	
	public void play () {
		// 调用 this.方法
		System.out.println(this.getName() + " play with " + this.playmate.getName());
	}

	public Dog() {
		// 调用 super 构造器
		super();
	}

	public Dog(Dog playmate) {
		// 调用 this 构造器
		this();
		this.playmate = playmate;
	}
	
	
}
