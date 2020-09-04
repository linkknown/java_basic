package com.linkknown.oop;

// ��ͨ������
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
		System.out.println("��ͨ�������Ǳ��˵�...");
	}
	// �� showDesc ���أ�ͬʱҲ��д�˸���\���ӿ� �ķ���
	@Override
	public void showDesc(boolean shortFlag) {
		if (shortFlag) {
			System.out.println("��ͨ������");
		} else {
			this.showDesc();
		}
	}
	
}
