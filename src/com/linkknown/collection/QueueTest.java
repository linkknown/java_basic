package com.linkknown.collection;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

/**
 * ���У��Ƚ��ȳ���������
 * @author Administrator
 *
 */
public class QueueTest {
	/**
	 * ��֤�����Ƚ��ȳ�
	 */
	@Test
	public void testQueue () {
		Queue<String> queue = new LinkedList<>();
		for (String s : "hello world hollo linkknown".split(" ")) {
			// ����
			queue.offer(s);
		}
		
		while (!queue.isEmpty()) {
			// ��ȡ���Ƴ��˶��е�ͷ
			System.out.println(queue.poll());
		}
	}
}
