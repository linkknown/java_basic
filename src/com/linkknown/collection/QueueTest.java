package com.linkknown.collection;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

/**
 * 队列（先进先出）测试类
 * @author Administrator
 *
 */
public class QueueTest {
	/**
	 * 验证队列先进先出
	 */
	@Test
	public void testQueue () {
		Queue<String> queue = new LinkedList<>();
		for (String s : "hello world hollo linkknown".split(" ")) {
			// 插入
			queue.offer(s);
		}
		
		while (!queue.isEmpty()) {
			// 获取并移除此队列的头
			System.out.println(queue.poll());
		}
	}
}
