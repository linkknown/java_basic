package com.linkknown.enum0;

/**
 * 登录状态枚举
 */
public enum LoginStateEnum {

	LOGIN_SUCCESS, LOGIN_FAILED;

	// 构造函数
	private LoginStateEnum() {
		System.out.println("调用了枚举构造器~");
	}

	public void showDesc() {
		System.out.println(this.toString());
	}
}
