package com.linkknown.oop;

public class Animal {
	
	static {
		System.out.println("���Ǿ�̬�����,�ұ�ִ����~");
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		// ���� this.����
		this.name = name;
	}

	/**
	 * ˵��
	 */
	public void talk() {

	}

	/**
	 * ˯��
	 */
	public void sleep() {
		System.out.println("��ʼ˯����~");
	}

	public final void finalMethod () {
		System.out.println("���� final �������ܱ���д~");
	}
}
