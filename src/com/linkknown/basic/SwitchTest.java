package com.linkknown.basic;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class SwitchTest {

	@Test
	public void testSwitch() {
		// ���ǿ���ͨ�� Scanner ������ȡ�û�����ֵ
		Scanner scanner = new Scanner(System.in);
		String grade = scanner.next();
		switch (grade) {
		case "A":
			System.out.println("����");
			break;
		case "B":
		case "C":
			System.out.println("����");
			break;
		case "D":
			System.out.println("����");
			break;
		case "E":
		case "F":
			System.out.println("����Ҫ��Ŭ��Ŭ��");
			break;
		default:
			System.out.println("δ֪�ȼ�");
			break;
		}
		System.out.println("��ĵȼ��ǣ�" + grade);
	}

	/**
	 * break ��д,���һ��case��Ӧ��ֵ�ɹ�,���ڲ�û��break���,��ô����������(���ٽ���caseƥ��)
	 * �ļ�������ִ������case�е����,ֱ������break;�����ߵ���switch������.
	 */
	@Test
	public void testSwitch2() {
		Scanner scanner = new Scanner(System.in);
		String grade = scanner.next();
		switch (grade) {
		case "A":
			System.out.println("����");
		case "B":
		case "C":
			System.out.println("����");
		case "D":
			System.out.println("����");
		case "E":
		case "F":
			System.out.println("����Ҫ��Ŭ��Ŭ��");
		default:
			System.out.println("δ֪�ȼ�");
		}
		System.out.println("��ĵȼ��ǣ�" + grade);
	}
}
