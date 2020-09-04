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
		System.out.println("������Ʒ�Ĳ���һ��" + first.toString());
		System.out.println("������Ʒ�Ĳ������" + second.toString());
		System.out.println("������Ʒ�Ĳ�������" + third.toString());
		System.out.println("������Ʒ�Ĳ����ģ�" + four.toString());
	}
	
}
