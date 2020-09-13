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
 * ���У��Ƚ��ȳ���������
 * @author Administrator
 *
 */
public class QueueTest {
	/**
	 * ���� LinkedList ������У��Ƚ��ȳ�
	 * 
	 * public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, java.io.Serializable
	 * 
	 * queue ������У��Ƚ��ȳ�
	 * poll(): �����׵�Ԫ��ɾ��,�����ظ�Ԫ��
	 * peek(): ���ض��׵�Ԫ��,��������ɾ������
	 * offer(): ��Ԫ����ӵ���β,����ɹ�,�򷵻�true
	 */
	@Test
	public void testQueue () {
		Queue<String> queue = new LinkedList<>();
		for (String s : "hello world hollo linkknown".split(" ")) {
			// offer(): ��β����
			queue.offer(s);
		}
		
		System.out.println(queue);
		
		System.out.println();
		// ���ض���Ԫ�ز��Ƴ�
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println();
		
		while (!queue.isEmpty()) {
			// ���ض���Ԫ�ز��Ƴ�
			System.out.println(queue.poll());
		}
	}
	
	
	/**
	 * ���� LinkedList ˫����У��Ƚ����(ջ)
	 *  
	 * LinkedList ���ɵ��ɵ������ʹ��,Ҳ�ɵ���˫�����ʹ��
	 * 
	 * LinkedList ����ջ��ʹ��
	 * ջ��
	 * push(e):��ջ,��ӵ�����
	 * peek():����ջ��Ԫ��,��������ɾ��
	 * pop():��ջ,ɾ������Ԫ��
	 */
	@Test
	public void testQueue2 () {
		Deque<String> queue = new LinkedList<>();
		for (String s : "hello world hollo linkknown".split(" ")) {
			// ���ײ���
			queue.push(s);
		}
		
		System.out.println(queue);
		
		System.out.println();
		// ���ض���Ԫ��,���Ƴ�
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println();
		
		while (!queue.isEmpty()) {
			// ���ض���Ԫ�ز��Ƴ�
			System.out.println(queue.pop());
		}
	}
	
	/**
	 * PriorityQueue(���ȶ���): ������Ȼ˳�����
	 */
	@Test
	public void testQueue3 () {
		Queue<Integer> queue = new PriorityQueue<>();
	
		for (int i=0; i<100; i++) {
			int randomNum = new Random().nextInt(100);
			System.out.print(randomNum + " ");
			
			// ��ӵ���β
			queue.offer(randomNum);
		}
		
		System.out.println();
		// ˳���Ѿ������˳��һ����,�ڲ��Զ�������
		System.out.println(queue);
		
		while (!queue.isEmpty()) {
			// ���ض���Ԫ��,������Ȼ˳�򷵻�
			System.out.print(queue.poll() + " ");
		}
	}
	
	
	/**
	 * PriorityQueue(���ȶ���): �����Զ���˳�����
	 * �Զ��壺�ȷ��������ٷ���ż������ͬ���ʵ���������Ȼ˳�򷵻�
	 * 
	 */
	@Test
	public void testQueue4 () {
		// �Զ���˳����Ҫʹ�����صĹ�����,�Զ��������ڲ��� Comparator ��ʵ���������
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// ����ż��
				if (o1 % 2 == 0 && o2 % 2 == 0) {
					return o1 - o2;
				}
				// ��������
				if (o1 % 2 == 1 && o2 % 2 == 1) {
					return o1 - o2;
				}
				// һ������һ��ż��
				
				return -(o1 % 2 - o2 % 2);
			}
		});
	
		for (int i=0; i<100; i++) {
			int randomNum = new Random().nextInt(100);
			System.out.print(randomNum + " ");
			
			// ��ӵ���β		
			// Դ�������public boolean offer(E e)   ->   siftUp(i, e);   ->    siftUpUsingComparator(k, x);  &&  siftUpComparable(k, x);
			queue.offer(randomNum);
		}
		
		System.out.println();
		// ˳���Ѿ������˳��һ����
		System.out.println(queue);
		
		while (!queue.isEmpty()) {
			// ���ض���Ԫ��,�����Զ���˳�򷵻�
			// Դ������� public E poll()  ->     siftDown(0, x);   ->    siftDownUsingComparator(k, x);  &&  siftDownComparable(k, x);
			System.out.print(queue.poll() + " ");
		}
	}
	
	public static void main(String[] args) {
		testBlockingQueue();
	}
	
	
	/**
	 * ������������: ʹ����������ʵ�ַ����Ͷ���
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














