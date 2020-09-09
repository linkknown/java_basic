package com.linkknown.oop.calculator;

// 计算器接口
public interface Calculator {
	// 根据类型获取操作运算
	Operator getOperator (String type);
	// 显示描述
	void showDesc();
	// 是否显示简短描述, 与 showDesc 重载
	void showDesc(boolean shortFlag);
}


