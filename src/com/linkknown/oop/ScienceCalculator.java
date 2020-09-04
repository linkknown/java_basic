package com.linkknown.oop;

// 科学计算器
public abstract class ScienceCalculator implements Calculator {
	
	@Override
	public void showDesc() {
		System.out.println("科学计算器是昂贵的...");
	}
	// 获取价格
	public abstract double getPrice ();
	
}
