package com.linkknown.oop;

public class Cat extends Animal {

	@Override
	public void talk() {
		System.out.println("喵喵喵...");
	}

	public void eatMouse () {
		System.out.println("吃了一只老鼠！");
	}
	

//	public final void finalMethod () {
//		System.out.println("我是 final 方法不能被重写~");
//	}
}
