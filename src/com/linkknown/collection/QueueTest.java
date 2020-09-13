package com.linkknown.collection;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

/**
 * 队列（先进先出）测试类
 * @author Administrator
 *
 */
public class QueueTest {
	/**
	 * 测试 LinkedList 单向队列：先进先出
	 * 
	 * public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, java.io.Serializable
	 * 
	 * queue 单向队列：先进先出
	 * poll(): 将队首的元素删除,并返回该元素
	 * peek(): 返回队首的元素,但不进行删除操作
	 * offer(): 将元素添加到队尾,如果成功,则返回true
	 */
	@Test
	public void testQueue () {
		Queue<String> queue = new LinkedList<>();
		for (String s : "hello world hollo linkknown".split(" ")) {
			// offer(): 队尾插入
			queue.offer(s);
		}
		
		System.out.println(queue);
		
		System.out.println();
		// 返回队首元素不移除
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println();
		
		while (!queue.isEmpty()) {
			// 返回队首元素并移除
			System.out.println(queue.poll());
		}
	}
	
	
	/**
	 * 测试 LinkedList 双向队列：先进后出(栈)
	 *  
	 * LinkedList 即可当成单向队列使用,也可当成双向队列使用
	 * 
	 * LinkedList 当成栈来使用
	 * 栈：
	 * push(e):入栈,添加到队首
	 * peek():返回栈首元素,但不进行删除
	 * pop():出栈,删除队首元素
	 */
	@Test
	public void testQueue2 () {
		Deque<String> queue = new LinkedList<>();
		for (String s : "hello world hollo linkknown".split(" ")) {
			// 队首插入
			queue.push(s);
		}
		
		System.out.println(queue);
		
		System.out.println();
		// 返回队首元素,不移除
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println();
		
		while (!queue.isEmpty()) {
			// 返回队首元素并移除
			System.out.println(queue.pop());
		}
	}
	
	/**
	 * PriorityQueue(优先队列): 按照自然顺序出队
	 */
	@Test
	public void testQueue3 () {
		Queue<Integer> queue = new PriorityQueue<>();
	
		for (int i=0; i<100; i++) {
			int randomNum = new Random().nextInt(100);
			System.out.print(randomNum + " ");
			
			// 添加到队尾
			queue.offer(randomNum);
		}
		
		System.out.println();
		// 顺序已经和添加顺序不一致了,内部自动给排序
		System.out.println(queue);
		
		while (!queue.isEmpty()) {
			// 返回队首元素,按照自然顺序返回
			System.out.print(queue.poll() + " ");
		}
	}
	
	
	/**
	 * PriorityQueue(优先队列): 按照自定义顺序出队
	 * 自定义：先返回奇数再返回偶数，相同性质的数按照自然顺序返回
	 * 
	 */
	@Test
	public void testQueue4 () {
		// 自定义顺序需要使用重载的构造器,自定义匿名内部类 Comparator 来实现排序规则
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// 都是偶数
				if (o1 % 2 == 0 && o2 % 2 == 0) {
					return o1 - o2;
				}
				// 都是奇数
				if (o1 % 2 == 1 && o2 % 2 == 1) {
					return o1 - o2;
				}
				// 一个奇数一个偶数
				
				return -(o1 % 2 - o2 % 2);
			}
		});
	
		for (int i=0; i<100; i++) {
			int randomNum = new Random().nextInt(100);
			System.out.print(randomNum + " ");
			
			// 添加到队尾		
			// 源码分析：public boolean offer(E e)   ->   siftUp(i, e);   ->    siftUpUsingComparator(k, x);  &&  siftUpComparable(k, x);
			queue.offer(randomNum);
		}
		
		System.out.println();
		// 顺序已经和添加顺序不一致了
		System.out.println(queue);
		
		while (!queue.isEmpty()) {
			// 返回队首元素,按照自定义顺序返回
			// 源码分析： public E poll()  ->     siftDown(0, x);   ->    siftDownUsingComparator(k, x);  &&  siftDownComparable(k, x);
			System.out.print(queue.poll() + " ");
		}
	}
	
	public static void main(String[] args) {
		testBlockingQueue();
	}
	
	
	/**
	 * 测试阻塞队列: 使用阻塞队列实现发布和订阅
	 */
	public static void testBlockingQueue () {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
		
		Thread product = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i=0;
				while (true) {
					try {
						int randomNumber = new Random().nextInt(100);
						queue.put(randomNumber);
						
						System.out.println(String.format("put number index:%d, value: %d", i, randomNumber));
						
						TimeUnit.SECONDS.sleep(1);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					i++;
				}
			}
		});
		
		Thread consumer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i=0;
				while (true) {
					try {
						System.out.println(String.format("take number index:%d, value: %d", i, queue.take()));
						
						TimeUnit.SECONDS.sleep(2);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i++;
				}
			}
		});
		
		product.start();
		consumer.start();
	}
}














