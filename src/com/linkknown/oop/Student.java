package com.linkknown.oop;

public class Student extends Person implements Player,Singer {

	@Override
	public void work() {
		System.out.println("学生开始上课啦");
	}

	@Override
	public void eat() {
		super.eat();
		
		System.out.println("学生开始吃法啦~");
	}

	@Override
	public void play() {
		System.out.println("学生在操场玩耍~");
	}

	@Override
	public void sing() {
		System.out.println("学生在教室读课文~");
	}
}
