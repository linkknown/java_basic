package com.linkknown.generic;

public class MultiHolder<A,B,C,D> {

	private A first;
	private B second;
	private C third;
	private D four;
	
	public MultiHolder(A first, B second, C third, D four) {
		super();
		this.first = first;
		this.second = second;
		this.third = third;
		this.four = four;
	}
	
	public void make () {
		System.out.println("生产产品的步骤一：" + first.toString());
		System.out.println("生产产品的步骤二：" + second.toString());
		System.out.println("生产产品的步骤三：" + third.toString());
		System.out.println("生产产品的步骤四：" + four.toString());
	}
	
}
