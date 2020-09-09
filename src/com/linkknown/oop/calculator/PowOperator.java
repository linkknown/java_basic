package com.linkknown.oop.calculator;

import com.linkknown.oop.Operator;

public class PowOperator implements Operator {

	@Override
	public int eval(int... numbers) {
		return (int) Math.pow(numbers[0], numbers[1]);
	}

}
