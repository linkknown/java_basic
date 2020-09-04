package com.linkknown.oop;

public class PowOperator implements Operator {

	@Override
	public int eval(int... numbers) {
		return (int) Math.pow(numbers[0], numbers[1]);
	}

}
