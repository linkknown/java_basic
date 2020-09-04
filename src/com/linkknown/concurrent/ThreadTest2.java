package com.linkknown.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

/**
 * 测试线程安全问题
 * 
 * @author Administrator
 *
 */
public class ThreadTest2 {

	static void sleep(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static int count = 0;

	/**
	 * i++ 是线程安全的吗 不是原子操作，分为三步：取、改、存
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testThread() throws InterruptedException {

		CountDownLatch countDownLatch = new CountDownLatch(1000);

		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						ThreadTest2.count++;
					}
					countDownLatch.countDown();
				}
			}).start();
		}

		countDownLatch.await();
		// 打印的会是 1000000 么？
		System.out.println(ThreadTest2.count);

	}

	/**
	 * 测试非原子操作
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testThreadUnSafeInteger() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1000);

		ThreadUnSafeInteger unSafeInteger = new ThreadUnSafeInteger(0);

		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						// add 方法不是院子操作
						unSafeInteger.add(1);
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		countDownLatch.await();
		System.out.println(unSafeInteger.get());
	}

	/**
	 * 测试非原子操作
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testThreadUnSafeInteger2() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1000);

		ThreadUnSafeInteger unSafeInteger = new ThreadUnSafeInteger(0);

		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						if (j % 2 == 0) {
							unSafeInteger.add(1);
						} else {
							unSafeInteger.sub(1);
						}
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		countDownLatch.await();
		System.out.println(unSafeInteger.get());
	}

	/**
	 * 测试原子操作
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testThreadSafeInteger() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1000);

		ThreadSafeInteger safeInteger = new ThreadSafeInteger(0);

		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						// add\add2 方法加锁，保证了原子操作
						safeInteger.add(1);
						// safeInteger.add2(1);
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		countDownLatch.await();
		System.out.println(safeInteger.get());
	}

	/**
	 * 测试原子操作
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testThreadSafeInteger2() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1000);

		ThreadSafeInteger safeInteger = new ThreadSafeInteger(0);

		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						if (j % 2 == 0) {
							// add\add2\sub\sub2 方法加锁，保证了原子操作
							safeInteger.add(1);
							// safeInteger.add2(1);
						} else {
							// safeInteger.unSafeSub(1); // 涉及类的所有方法都得加锁才行，局部加锁是线程不安全的
							safeInteger.sub(1);
							// safeInteger.sub2(1);
						}
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		countDownLatch.await();
		System.out.println(safeInteger.get());
	}

	// /**
	// * +\StringBuilder\StringBuffer 性能测试
	// *
	// */
	// @Test
	// public void testStringAppend () {
	// int appendCount = 100000;
	// // 测试 +
	// String str = "";
	// StringBuilder sbd = new StringBuilder();
	// StringBuffer sbf = new StringBuffer();
	//
	// long startTime = new GregorianCalendar().getTimeInMillis();
	// long startMemory = Runtime.getRuntime().freeMemory();
	// for (int i=0; i< appendCount; i++) {
	// str = str + i; // + 会创建大量新对象
	// }
	// long endMemory = Runtime.getRuntime().freeMemory();
	// long endTime = new GregorianCalendar().getTimeInMillis();
	// System.out.println("+ cost " + (endTime - startTime) + "ms");
	// System.out.println("+ cost memory " + String.valueOf(startMemory -
	// endMemory));
	//
	// startTime = new GregorianCalendar().getTimeInMillis();
	// startMemory = Runtime.getRuntime().freeMemory();
	// for (int i=0; i< appendCount; i++) {
	// sbd.append(i);
	// }
	// endMemory = Runtime.getRuntime().freeMemory();
	// endTime = new GregorianCalendar().getTimeInMillis();
	// System.out.println("StringBuilder cost " + (endTime - startTime) + "ms");
	// System.out.println("StringBuilder cost memory " + (startMemory - endMemory));
	//
	// startTime = new GregorianCalendar().getTimeInMillis();
	// startMemory = Runtime.getRuntime().freeMemory();
	// for (int i=0; i< appendCount; i++) {
	// sbf.append(i);
	// }
	// endMemory = Runtime.getRuntime().freeMemory();
	// endTime = new GregorianCalendar().getTimeInMillis();
	// System.out.println("StringBuffer cost " + (endTime - startTime) + "ms");
	// System.out.println("StringBuffer cost memory " + (startMemory - endMemory));
	// }

	/**
	 * 测试 StringBuilder 线程安全性
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testStringBuilder() throws InterruptedException {
		StringBuilder sb = new StringBuilder();

		CountDownLatch countDownLatch = new CountDownLatch(100);

		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						sb.append("1");
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		countDownLatch.await();

		System.out.println(sb.length());
	}

	/**
	 * 测试 StringBuffer 线程安全性
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testStringBuffer() throws InterruptedException {
		StringBuffer sb = new StringBuffer();

		CountDownLatch countDownLatch = new CountDownLatch(100);

		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						// StringBuffer 为什么线程安全
						// StringBuffer 底层源码是 StringBuilder + 锁
						sb.append("1");
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		countDownLatch.await();

		System.out.println(sb.length());
	}

	/**
	 * 测试 ArrayList 的线程安全性
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testArrayList() throws InterruptedException {
		List<Integer> lst = new ArrayList<Integer>();

		CountDownLatch countDownLatch = new CountDownLatch(100);

		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						// ArrayList 线程不安全
						lst.add(j);
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		countDownLatch.await();
		System.out.println(lst.size());
	}

	/**
	 * 线程安全的 List
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testList() throws InterruptedException {
		/**
		 * Copy-On-Write，写入时复制，这个技术，准确的说应该是一种思想，在很多系统设计上都会用到，今天我们来谈一谈Java语言中，
		 * JDK运用这种写入时复制的思想的数据结构/容器，CopyOnWriteArrayList。CopyOnWriteArrayList，是一个写入时复制的容器，
		 * 它是如何工作的呢？简单来说，就是平时查询的时候，都不需要加锁，随便访问，只有在写入/删除的时候，才会从原来的数据复制一个副本出来，
		 * 然后修改这个副本，最后把原数据替换成当前的副本。修改操作的同时，读操作不会被阻塞，而是继续读取旧的数据。这点要跟读写锁区分一下。
		 */
		List<Integer> lst = new CopyOnWriteArrayList<>();

		CountDownLatch countDownLatch = new CountDownLatch(100);

		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						// ArrayList 线程不安全
						lst.add(j);
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		countDownLatch.await();
		System.out.println(lst.size());
	}

	/**
	 * 线程安全的 List
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testList2() throws InterruptedException {
		// Collections.synchronizedList 也是线程安全的 List
		List<Integer> lst = Collections.synchronizedList(new ArrayList<>());

		CountDownLatch countDownLatch = new CountDownLatch(100);

		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						lst.add(j);
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		countDownLatch.await();
		System.out.println(lst.size());
	}

//	/**
//	 * 测试 List 迭代 bug
//	 */
//	public static void main(String[] args) {
//		List<Integer> lst = new ArrayList<>();
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				for (int i = 0; i < 1000; i++) {
//					lst.add(i);
//				}
//			}
//		}).start();
//		Iterator<Integer> iterator = lst.iterator();
//		while (iterator.hasNext()) {
//			Integer num = (Integer) iterator.next();
//			System.out.println(num);
//		}
//	}
//	
	
//	/**
//	 * 安全地读取配置文件
//	 */
//	public static void main(String[] args) {
//		for (int i=0; i<10; i++) {
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
////					System.out.println(AccountUtil.getInstance());		// 没加锁，并发不安全
////					System.out.println(AccountUtil.getInstance2());		// 方法加锁，并发安全
////					System.out.println(AccountUtil.getInstance3());		// 代码块加锁，synchronized 外部判空，不安全（ 外部判空，不安全,读操作没有加锁）
////					System.out.println(AccountUtil.getInstance4());		// 代码块加锁，线程安全
//					System.out.println(AccountUtil.getInstance5());		// 代码块加锁，线程安全, (单元测试类下跑会有 bug)
//					
//				}
//			}).start();
//		}
//	}
//	
	
	private static AtomicInteger safeCount = new AtomicInteger(0);
	
	/**
	 * AtomicInteger 原子类,底层使用 volitate 关键词,是线程安全的类
	 * @throws InterruptedException
	 */
	@Test
	public void testAtomicInteger () throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1000);

		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						ThreadTest2.safeCount.addAndGet(1);
					}
					countDownLatch.countDown();
				}
			}).start();
		}

		countDownLatch.await();
		// 打印的会是 1000000 么？
		System.out.println(ThreadTest2.safeCount.get());

	}
}
