package com.linkknown.generic;

import java.util.Random;

/**
 * ����
 * @author Administrator
 *
 */
public class Bird extends Animal {
	
	public void fly () {
		Random random = new Random();
		int randomNum = random.nextInt(100);
		System.out.println(String.format("�� %d �����Ϸɣ�", randomNum));
	}
}
