package com.linkknown.oop;

public class AddOperator implements Operator {

	@Override
	public int eval(int... numbers) {
		return numbers[0] + numbers[1];
	}
}
