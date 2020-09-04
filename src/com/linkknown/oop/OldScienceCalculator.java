package com.linkknown.oop;

// 老式科学计算器
public class OldScienceCalculator extends ScienceCalculator {
	
	public static final double PRICE = 500.00;
	
	@Override
	public double getPrice() {
		return PRICE;
	}
	
	@Override
	public void showDesc() {
		System.out.println("老式的科学计算器是值得收藏的，价格不贵也不便宜...");
	}

	@Override
	public void showDesc(boolean shortFlag) {
		if (shortFlag) {
			System.out.println("老式科学计算器");
		} else {
			this.showDesc();
		}
	}

	@Override
	public Operator getOperator(String type) {
		Operator operator = null;
		switch (type) {
		case "^":
			operator = new PowOperator();
			break;
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
}
