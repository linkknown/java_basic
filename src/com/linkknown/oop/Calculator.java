package com.linkknown.oop;

// �������ӿ�
public interface Calculator {
	// �������ͻ�ȡ��������
	Operator getOperator (String type);
	// ��ʾ����
	void showDesc();
	// �Ƿ���ʾ�������, �� showDesc ����
	void showDesc(boolean shortFlag);
}


