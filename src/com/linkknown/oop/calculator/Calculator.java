package com.linkknown.oop.calculator;

import com.linkknown.oop.Operator;

// �������ӿ�
public interface Calculator {
	// �������ͻ�ȡ��������
	Operator getOperator (String type);
	// ��ʾ����
	void showDesc();
	// �Ƿ���ʾ�������, �� showDesc ����
	void showDesc(boolean shortFlag);
}


