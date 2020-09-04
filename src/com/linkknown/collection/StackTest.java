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
		for (String s : "hello world hollo linkknown".split(" ")) {
			stack.push(s);
		}
		
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
