package com.linkknown.oop;

public class Dog extends Animal {
	
	/**
	 * ���
	 */
	private Dog playmate;
	
	@Override
	public void talk() {
		System.out.println("������...");
	}
	
	/**
	 * �ظ��Ľ�
	 * @param repeatcount
	 */
	public void talk (int repeatcount) {
		for (int i=0; i< repeatcount; i++) {
			this.talk();
		}
	}

	public Dog getPlaymate() {
		return playmate;
	}

	public void setPlaymate(Dog playmate) {
		this.playmate = playmate;
	}
	
	public void play () {
		// ���� this.����
		System.out.println(this.getName() + " play with " + this.playmate.getName());
	}

	public Dog() {
		// ���� super ������
		super();
	}

	public Dog(Dog playmate) {
		// ���� this ������
		this();
		this.playmate = playmate;
	}
	
	
}
