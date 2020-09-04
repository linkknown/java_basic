package com.linkknown.oop;

// ��ʽ��ѧ������
public class NewScienceCalculator extends ScienceCalculator {
	
	public static final double PRICE = 1000.00;
	
	@Override
	public double getPrice() {
		return PRICE;
	}

	@Override
	public void showDesc(boolean shortFlag) {
		if (shortFlag) {
			System.out.println("��ʽ��ѧ������");
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
