package com.linkknown.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * 测试 List 有序集合
 * 
 * @author Administrator
 *
 */
public class ListTest {

	/**
	 * 测试 add 方法,底层 ensureCapacityInternal(size + 1) 用来扩容
	 * 
	 *   public boolean add(E e) {
	 *       ensureCapacityInternal(size + 1);  // Increments modCount!!
	 *       elementData[size++] = e;
	 *       return true;
	 *   }
	 *   
	 */
	@Test
	public void testArrayList01() {
		List<Integer> lst = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			lst.add(i);
		}

		System.out.println(lst);
	}

	/**
	 * 测试 addAll 方法
	 */
	@Test
	public void testArrayList02() {
		List<Integer> lst = new ArrayList<>();

		lst.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

		System.out.println(lst);
	}

	/**
	 * 测试 list 有序性、可重复性、可以放入 null
	 */
	@Test
	public void testListRepeat () {
		List<String> lst = new ArrayList<>();
		lst.add("hello");
		lst.add("world");
		lst.add(null);
		lst.add("hello");
		lst.add("world");
		lst.add(null);
		System.out.println(lst);
		
		lst = new LinkedList<>();
		lst.add("hello");
		lst.add("world");
		lst.add(null);
		lst.add("hello");
		lst.add("world");
		lst.add(null);
		System.out.println(lst);
	}
	
	/**
	 * 测试 contains\containsAll\... 等方法
	 */
	@Test
	public void testArrayList03() {
		List<Integer> lst = new ArrayList<>();

		lst.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		System.out.println(lst.contains(4));
		System.out.println(lst.containsAll(Arrays.asList(11, 12)));
		System.out.println(lst.size());
		System.out.println(lst.isEmpty());
		System.out.println(lst.subList(0, 5));
	}

	/**
	 * List 遍历
	 */
	@Test
	public void testArrayList04() {
		List<Integer> lst = new ArrayList<>();

		lst.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

		// 增强 for 循环
		for (int num : lst) {
			System.out.println(num);
		}

		System.out.println();

		// 迭代器
		Iterator<Integer> iterator = lst.iterator();
		while (iterator.hasNext()) {
			int entry = iterator.next();
			System.out.println(entry);
		}

		System.out.println();

		// forEach 遍历
		lst.forEach(num -> System.out.println(num));
	}

	/**
	 * 测试数组、arraylist、linkedlist 随机访问和遍历性能比较
	 */
	@Test
	public void testArrayListAndLinkedList() {
		final int MAX = 100000;				// 分别测试 10、100、1000、10000、100000 的性能
		long start, end;
		int arr[] = new int[MAX];
		ArrayList<Integer> arraylist = new ArrayList<>();
		LinkedList<Integer> linklist = new LinkedList<>();
		/* 初始化各个数组链表 */
		for (int i = 0; i < MAX; i++) {
			arr[i] = i;
			arraylist.add(i);
			linklist.add(i);
		}
		/* 随机访问测试 */
		System.out.println("随机访问测试(" + MAX + "):");
		start = System.nanoTime();
		int tmp;
		for (int i = 0; i < MAX; i++)
			tmp = arr[i];
		end = System.nanoTime();
		System.out.println("数组:    		" + (end - start));
		start = System.nanoTime();
		for (int i = 0; i < MAX; i++)
			tmp = arraylist.get(i);
		end = System.nanoTime();
		System.out.println("arraylist:	" + (end - start));
		start = System.nanoTime();
		for (int i = 0; i < MAX; i++)
			tmp = linklist.get(i);
		end = System.nanoTime();
		System.out.println("linkedlist:	" + (end - start));
		/* 遍历测试 */
		System.out.println("遍历测试(" + MAX + "):");
		start = System.nanoTime();
		for (int e : arr)
			tmp = e;
		end = System.nanoTime();
		System.out.println("数组:    		" + (end - start));
		start = System.nanoTime();
		for (int e : arraylist)
			tmp = e;
		end = System.nanoTime();
		System.out.println("arraylist:	" + (end - start));
		start = System.nanoTime();
		for (int e : linklist)
			tmp = e;
		end = System.nanoTime();
		System.out.println("linkedlist:	" + (end - start));
	}
	
	
	/**
	 * arraylist、linkedlist 的使用场景
	 */
	@Test
	public void testArrayListAndLinkedList2 () {
		long start = System.nanoTime();
		List<Integer> lst = new ArrayList<>();
		// 使用 List 存储最近 30 天的天气
		for (int i = 0; i < 365; i++) {
			lst.add(new Random().nextInt(40));
			if (lst.size() > 30) {
				lst.remove(0);
			}
		}
		long end = System.nanoTime();
		System.out.println(end - start);
		
		start = System.nanoTime();
		lst = new LinkedList<>();
		// 使用 List 存储最近 30 天的天气
		for (int i = 0; i < 365; i++) {
			lst.add(new Random().nextInt(40));
			if (lst.size() > 30) {
				lst.remove(0);
			}
		}
		end = System.nanoTime();
		System.out.println(end - start);
	}
	
	
	/**
	 * 优化：知道数据长度的时候性能优化
	 * 初始化的时候指定长度肯定是要比不指定长度的性能好很多, 这样不用重复的申请空间, 复制数组, 销毁老的分配空间
	 */
	@Test
	public void testArrayList05 () {
		long start = System.nanoTime();
		List<String> lst = new ArrayList<>();
		for (int i=0; i< 1000; i++) {
			lst.add("hello");
		}
		long end = System.nanoTime();
		System.out.println(end - start);
		
		
		start = System.nanoTime();
		// 创建 List 的时候指定长度
		lst = new ArrayList<>(1000);
		for (int i=0; i< 1000; i++) {
			lst.add("hello");
		}
		end = System.nanoTime();
		System.out.println(end - start);
	}
	
	/**
	 * 设置初始长度的时候要合理,小了会自动扩容，大了会浪费
	 */
	@Test
	public void testArrayList06 () {
		List<String> lst = new ArrayList<>(100);
		for (int i=0; i< 1000; i++) {
			lst.add("hello");
		}
		System.out.println(lst.size());
	}

	/**
	 * 遍历时删除元素
	 * 使用迭代器的删除可靠些
	 */
	@Test
	public void testRemove () {
		List<String> lst = new ArrayList<>(100);
		for (int i=0; i< 1000; i++) {
			lst.add(new Random().nextInt(100) + "");
		}
		for(String s: lst){  
		    if(Integer.parseInt(s) < 60){  
		        lst.remove(s);  
		    }  
		}  
		System.out.println(lst); 
	}
	
	/**
	 * 遍历时删除元素
	 * 使用迭代器的删除可靠些
	 */
	@Test
	public void testRemove2 () {
		List<String> lst = new ArrayList<>(100);
		for (int i=0; i< 1000; i++) {
			lst.add(new Random().nextInt(100) + "");
		}
		
		System.out.println(lst);
		Iterator<String> iterator = lst.iterator();
		while (iterator.hasNext()) {
			String number = iterator.next();
			if (Integer.parseInt(number) < 60) {
				iterator.remove();
			}
		}
		System.out.println(lst);
	}
}
