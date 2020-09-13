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
		for (String str : "hello world hollo linkknown".split(" ")) {
			// 入栈(往队尾添加元素)
			stack.push(str);
		}
		
		System.out.println(stack);
		
		while (!stack.empty()) {
			// 出栈(从栈顶移除元素)
			System.out.println(stack.pop());
		}
	}
}
