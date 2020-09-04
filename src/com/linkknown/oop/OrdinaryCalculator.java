package com.linkknown.oop;

// 普通计算器
public class OrdinaryCalculator implements Calculator {
	@Override
	public Operator getOperator(String type) {
		Operator operator = null;
		switch (type) {
		case "+":
			operator = new AddOperator();
			break;
		case "-":
			operator = new SubOperator();
			break;
		case "*":
			operator = new  MulOperator();
			break;
		case "/":
			operator = new DivOperator();
			break;
		default:
			break;
		}
		return operator;
	}
	@Override
	public void showDesc() {
		System.out.println("普通计算器是便宜的...");
	}
	// 与 showDesc 重载，同时也重写了父类\父接口 的方法
	@Override
	public void showDesc(boolean shortFlag) {
		if (shortFlag) {
			System.out.println("普通计算器");
		} else {
			this.showDesc();
		}
	}
	
}
