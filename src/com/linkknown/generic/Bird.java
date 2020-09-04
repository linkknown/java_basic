package com.linkknown.generic;

import java.util.Random;

/**
 * 鸟类
 * @author Administrator
 *
 */
public class Bird extends Animal {
	
	public void fly () {
		Random random = new Random();
		int randomNum = random.nextInt(100);
		System.out.println(String.format("鸟 %d 在天上飞！", randomNum));
	}
}
