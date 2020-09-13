package com.linkknown.collection;

import java.util.Stack;

import org.junit.jupiter.api.Test;

/**
 * ջ���Ƚ������������
 * @author Administrator
 *
 */
public class StackTest {

	/**
	 * ��֤ջ�Ƚ����
	 */
	@Test
	public void testStack () {
		Stack<String> stack = new Stack<>();
		for (String str : "hello world hollo linkknown".split(" ")) {
			// ��ջ(����β���Ԫ��)
			stack.push(str);
		}
		
		System.out.println(stack);
		
		while (!stack.empty()) {
			// ��ջ(��ջ���Ƴ�Ԫ��)
			System.out.println(stack.pop());
		}
	}
}
