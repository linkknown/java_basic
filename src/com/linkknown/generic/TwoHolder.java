package com.linkknown.generic;

public class TwoHolder<A, B> {

	private A first;
	private B second;

	public TwoHolder(A first, B second) {
		super();
		this.first = first;
		this.second = second;
	}

	public boolean checkEq () {
		return first.equals(second);
	}
}
