package com.linkknown.oop.calculator;

public class DivOperator implements Operator {

	@Override
	public int eval(int... numbers) {
		return numbers[0] / numbers[1];
	}

}
