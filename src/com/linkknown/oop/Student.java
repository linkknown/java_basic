package com.linkknown.oop;

public class Student extends Person implements Player,Singer {

	@Override
	public void work() {
		System.out.println("ѧ����ʼ�Ͽ���");
	}

	@Override
	public void eat() {
		super.eat();
		
		System.out.println("ѧ����ʼ�Է���~");
	}

	@Override
	public void play() {
		System.out.println("ѧ���ڲٳ���ˣ~");
	}

	@Override
	public void sing() {
		System.out.println("ѧ���ڽ��Ҷ�����~");
	}
}
