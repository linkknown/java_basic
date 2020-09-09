package com.linkknown.oop.calculator;

public class MulOperator implements Operator {

	@Override
	public int eval(int... numbers) {
		return numbers[0] * numbers[1];
	}

}
