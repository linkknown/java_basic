package com.linkknown.collection;

import java.util.Stack;

import org.junit.jupiter.api.Test;

/**
 * 栈（先进后出）测试类
 * @author Administrator
 *
 */
public class StackTest {

	/**
	 * 验证栈先进后出
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
