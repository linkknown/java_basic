package com.linkknown.oop;

// ��ѧ������
public abstract class ScienceCalculator implements Calculator {
	
	@Override
	public void showDesc() {
		System.out.println("��ѧ�������ǰ����...");
	}
	// ��ȡ�۸�
	public abstract double getPrice ();
	
}
