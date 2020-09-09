package com.linkknown.oop;

import com.linkknown.oop.calculator.Operator;

public class SubOperator implements Operator {

	@Override
	public int eval(int... numbers) {
		return numbers[0] - numbers[1];
	}

}
