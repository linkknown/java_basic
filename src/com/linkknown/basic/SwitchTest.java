package com.linkknown.basic;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class SwitchTest {

	@Test
	public void testSwitch () {
		// 我们可以通过 Scanner 类来获取用户输入值
		Scanner scanner = new Scanner(System.in);
		String grade = scanner.next();
		switch (grade) {
		case "A":
			System.out.println("优秀");
			break;
		case "B":
		case "C":
			System.out.println("良好");
			break;
		case "D":
			System.out.println("及格");
			break;
		case "E":
		case "F":
			System.out.println("你需要再努力努力");
			break;
		default:
			System.out.println("未知等级");
			break;
		}
		
		System.out.println("你的等级是：" + grade);
	}
}
