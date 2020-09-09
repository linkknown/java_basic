package com.linkknown.oop.calculator;

import com.linkknown.oop.Operator;

public class AddOperator implements Operator {

	@Override
	public int eval(int... numbers) {
		return numbers[0] + numbers[1];
	}
}
