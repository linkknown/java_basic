package com.linkknown.basic;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class SwitchTest {

	@Test
	public void testSwitch() {
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

	/**
	 * break 不写,如果一旦case相应的值成功,但内部没有break语句,那么将会无条件(不再进行case匹配)
	 * 的继续向下执行其它case中的语句,直到遇到break;语句或者到达switch语句结束.
	 */
	@Test
	public void testSwitch2() {
		Scanner scanner = new Scanner(System.in);
		String grade = scanner.next();
		switch (grade) {
		case "A":
			System.out.println("优秀");
		case "B":
		case "C":
			System.out.println("良好");
		case "D":
			System.out.println("及格");
		case "E":
		case "F":
			System.out.println("你需要再努力努力");
		default:
			System.out.println("未知等级");
		}
		System.out.println("你的等级是：" + grade);
	}
}
