package com.linkknown.enum0;

/**
 * ��¼״̬ö��
 */
public enum LoginStateEnum {

	LOGIN_SUCCESS, LOGIN_FAILED;

	// ���캯��
	private LoginStateEnum() {
		System.out.println("������ö�ٹ�����~");
	}

	public void showDesc() {
		System.out.println(this.toString());
	}
}
