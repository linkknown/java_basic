package com.linkknown.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
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
	
	public static void main(String[] args) throws Exception {
//		testThread();
//		testThreadUnSafeInteger();
//		testThreadUnSafeInteger2();
//		testThreadSafeInteger();
//		testThreadSafeInteger2();
//		testStringBuilder();
//		testStringBuffer();
//		testArrayList();
//		testCopyOnWriteArrayList();
//		testSynchronizedList();
//		testAtomicInteger();
//		testReOrder();
//		testReadFle();
		testPrintABC123();
	}

	static int count = 0;

	/**
	 * i++ 是线程安全的吗? 不是原子操作，分为三步：取、改、存
	 * 
	 * @throws InterruptedException
	 */
	public static void testThread() throws InterruptedException {

		CountDownLatch countDownLatch = new CountDownLatch(1000);

		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						count++;
					}
					countDownLatch.countDown();
				}
			}).start();
		}

		countDownLatch.await();
		// 打印的会是 1000000 么？
		System.out.println(count);
	}

	/**
	 * 测试非原子操作
	 */
	public static void testThreadUnSafeInteger() throws InterruptedException {
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
	public static void testThreadUnSafeInteger2() throws InterruptedException {
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
	public static void testThreadSafeInteger() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1000);

		ThreadSafeInteger safeInteger = new ThreadSafeInteger(0);

		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						safeInteger.add(1);
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
	public static void testThreadSafeInteger2() throws InterruptedException {
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
	 */
	public static void testStringBuilder() throws InterruptedException {
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
	 */
	public static void testStringBuffer() throws InterruptedException {
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
	 */
	public static void testArrayList() throws InterruptedException {
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
	 */
	public static void testCopyOnWriteArrayList() throws InterruptedException {
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
	 */
	public static void testSynchronizedList() throws InterruptedException {
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

	
	/**
	 * 验证指令重排（有序性）
	 */
    static Integer a = 0;
    static Integer b = 0;
    static Integer x = 0;
    static Integer y = 0;
    
    /**
     *用来演示指令重排
     *
     * 指令重排会发生在两个阶段：
 	 * 1. 编译期(jvm 加载字节码时)
 	 * 2. cpu 执行期
 	 * 但对于单线程来说，不管发生怎样的重排，都必须保持与源代码一致的输出结果（As-If-Serial）.
 	 * 上述规则保证了单线程的执行结果总是与预期一致，但在多线程的情况，就会出现与预期不一致的情况，
 	 * 而导致这一情况发生的原因，正是指令重排
 	 * 
 	 * 执行正常场景有： a = 1; => x = b; => b = 1; => y = a; 				打印 第 ?? 次，x=0, y=1
 	 * 			   b = 1; => y = a; => a = 1; => x = b; 				打印 第 ?? 次，x=1, y=0
 	 * 			   a = 1; => b = 1; => x = b; => y = a; 				打印 第 ?? 次，x=1, y=1
 	 * 
 	 * 指令重排异常场景有：
 	 * 			   x = b; => y = a; => a = 1; => b = 1; 				打印 第 ?? 次，x=0, y=0
     * @throws InterruptedException 
 	 * 
     */
	public static void testReOrder () throws InterruptedException {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					// 有可能发生重排，即 先执行 x = b,再执行 a = 1
					a = 1;
					x = b;
				}
			});

			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					// 有可能发生重排，即先执行 y = a,再执行 b = 1;	
					b = 1;
					y = a;
				}
			});

			t1.start();
			t2.start();
			// t1 线程插队，比主线程先执行
			t1.join();
			// t2 线程插队，比主线程先执行
			t2.join();
			/**
			 * 如果没有指令重排，输出的可以结果为:(0,1)(1,1)(1,0) 但实际上有可能会输出(0,0)
			 */
			System.out.println("第 " + i + "次，x=" + x + ", y=" + y);
			if (x == 0 && y == 0) {
				System.out.println("发生了指令重排");
				break;
			}
			// 全部重置成 0
			a = b = 0;
			x = y = 0;
		}
	}
	
	/**
	 * 安全高效地读取配置文件
	 */
	public static void testReadFle () {
		for (int i=0; i<100; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(AccountUtil.getInstance());		
				}
			}).start();
		}
	}
	
	
//	public static void main(String[] args) throws InterruptedException {
	@Test
	public void testThreadSafeInteger3() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(10);

		ThreadSafeInteger2 safeInteger = new ThreadSafeInteger2(0);

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 10; j++) {
						if (j % 2 == 0) {
//							safeInteger.add(1);
//							safeInteger.add2(1);
							safeInteger.add(1);
						} else {
//							safeInteger.sub(1);
//							safeInteger.sub2(1);
							safeInteger.sub(1);
						}
					}
					countDownLatch.countDown();
					
//					System.out.println(safeInteger.get());
//					System.out.println(safeInteger.get2());
//					System.out.println(safeInteger.get3());
				}
			}).start();
		}
		countDownLatch.await();
//		System.out.println(safeInteger.get());
//		System.out.println(safeInteger.get2());
		System.out.println(safeInteger.get());
	}
	
	private static AtomicInteger safeCount = new AtomicInteger(0);
//	AtomicBoolean
//	AtomicReference
	
	/**
	 * AtomicInteger 原子类,底层使用 volitate 关键词,是线程安全的类
	 */
	public static void testAtomicInteger () throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1000);

		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						safeCount.addAndGet(1);
					}
					countDownLatch.countDown();
				}
			}).start();
		}

		countDownLatch.await();
		// 打印的会是 1000000 么？
		System.out.println(safeCount.get());
	}
	
	
	/**
	 * 练习：使用两个线程交替打印 123... 和 ABC...
	 * 
	 * object的wait()、notify()、notifyAll() 方法
	 * 
	 * wait()、notify()和notifyAll()是 Object类 中的方法
	 * 从这三个方法的文字描述可以知道以下几点信息：
	 * 1）wait()、notify()和notifyAll()方法是本地方法，并且为final方法，无法被重写。
	 * 2）调用某个对象的wait()方法能让当前线程阻塞，并且当前线程必须拥有此对象的monitor（即锁）
	 * 3）调用某个对象的notify()方法能够唤醒一个正在等待这个对象的monitor的线程，如果有多个线程都在等待这个对象的monitor，则只能唤醒其中一个线程；
	 * 4）调用notifyAll()方法能够唤醒所有正在等待这个对象的monitor的线程；
	 */
	public static void testPrintABC123 () {

		// 锁对象
		final Object obj = new Object();
		
		Runnable runnable_print123 = new Runnable() {
			
			@Override
			public void run() {
				synchronized (obj) {
					for (int i = 1;; i++) {
						System.out.println(i % 10);
						// 唤醒锁 obj 绑定的其它线程
						obj.notifyAll();
						try {
							// 阻塞线程，等待被唤醒
							obj.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}
				}
			}
		};

		Runnable runnable_printABC = new Runnable() {
			
			@Override
			public void run() {
				synchronized (obj) {
					for (int i = 'A'; i <= 'Z'; i++) {
						System.out.println((char)i);
						obj.notifyAll();
						try {
							obj.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};

		Thread thread1 = new Thread(runnable_print123);
		Thread thread2 = new Thread(runnable_printABC);
		
		thread1.start();
		thread2.start();
	}
	
}











