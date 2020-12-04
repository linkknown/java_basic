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
 * �����̰߳�ȫ����
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
	 * i++ ���̰߳�ȫ����? ����ԭ�Ӳ�������Ϊ������ȡ���ġ���
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
		// ��ӡ�Ļ��� 1000000 ô��
		System.out.println(count);
	}

	/**
	 * ���Է�ԭ�Ӳ���
	 */
	public static void testThreadUnSafeInteger() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1000);

		ThreadUnSafeInteger unSafeInteger = new ThreadUnSafeInteger(0);

		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						// add ��������Ժ�Ӳ���
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
	 * ���Է�ԭ�Ӳ���
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
	 * ����ԭ�Ӳ���
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
	 * ����ԭ�Ӳ���
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
							// add\add2\sub\sub2 ������������֤��ԭ�Ӳ���
							safeInteger.add(1);
							// safeInteger.add2(1);
						} else {
							// safeInteger.unSafeSub(1); // �漰������з������ü������У��ֲ��������̲߳���ȫ��
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
	// * +\StringBuilder\StringBuffer ���ܲ���
	// *
	// */
	// @Test
	// public void testStringAppend () {
	// int appendCount = 100000;
	// // ���� +
	// String str = "";
	// StringBuilder sbd = new StringBuilder();
	// StringBuffer sbf = new StringBuffer();
	//
	// long startTime = new GregorianCalendar().getTimeInMillis();
	// long startMemory = Runtime.getRuntime().freeMemory();
	// for (int i=0; i< appendCount; i++) {
	// str = str + i; // + �ᴴ�������¶���
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
	 * ���� StringBuilder �̰߳�ȫ��
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
	 * ���� StringBuffer �̰߳�ȫ��
	 */
	public static void testStringBuffer() throws InterruptedException {
		StringBuffer sb = new StringBuffer();

		CountDownLatch countDownLatch = new CountDownLatch(100);

		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						// StringBuffer Ϊʲô�̰߳�ȫ
						// StringBuffer �ײ�Դ���� StringBuilder + ��
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
	 * ���� ArrayList ���̰߳�ȫ��
	 */
	public static void testArrayList() throws InterruptedException {
		List<Integer> lst = new ArrayList<Integer>();

		CountDownLatch countDownLatch = new CountDownLatch(100);

		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						// ArrayList �̲߳���ȫ
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
	 * �̰߳�ȫ�� List
	 */
	public static void testCopyOnWriteArrayList() throws InterruptedException {
		/**
		 * Copy-On-Write��д��ʱ���ƣ����������׼ȷ��˵Ӧ����һ��˼�룬�ںܶ�ϵͳ����϶����õ�������������̸һ̸Java�����У�
		 * JDK��������д��ʱ���Ƶ�˼������ݽṹ/������CopyOnWriteArrayList��CopyOnWriteArrayList����һ��д��ʱ���Ƶ�������
		 * ������ι������أ�����˵������ƽʱ��ѯ��ʱ�򣬶�����Ҫ�����������ʣ�ֻ����д��/ɾ����ʱ�򣬲Ż��ԭ�������ݸ���һ������������
		 * Ȼ���޸��������������ԭ�����滻�ɵ�ǰ�ĸ������޸Ĳ�����ͬʱ�����������ᱻ���������Ǽ�����ȡ�ɵ����ݡ����Ҫ����д������һ�¡�
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
	 * �̰߳�ȫ�� List
	 */
	public static void testSynchronizedList() throws InterruptedException {
		// Collections.synchronizedList Ҳ���̰߳�ȫ�� List
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
	 * ��ָ֤�����ţ������ԣ�
	 */
    static Integer a = 0;
    static Integer b = 0;
    static Integer x = 0;
    static Integer y = 0;
    
    /**
     *������ʾָ������
     *
     * ָ�����Żᷢ���������׶Σ�
 	 * 1. ������(jvm �����ֽ���ʱ)
 	 * 2. cpu ִ����
 	 * �����ڵ��߳���˵�����ܷ������������ţ������뱣����Դ����һ�µ���������As-If-Serial��.
 	 * ��������֤�˵��̵߳�ִ�н��������Ԥ��һ�£����ڶ��̵߳�������ͻ������Ԥ�ڲ�һ�µ������
 	 * ��������һ���������ԭ������ָ������
 	 * 
 	 * ִ�����������У� a = 1; => x = b; => b = 1; => y = a; 				��ӡ �� ?? �Σ�x=0, y=1
 	 * 			   b = 1; => y = a; => a = 1; => x = b; 				��ӡ �� ?? �Σ�x=1, y=0
 	 * 			   a = 1; => b = 1; => x = b; => y = a; 				��ӡ �� ?? �Σ�x=1, y=1
 	 * 
 	 * ָ�������쳣�����У�
 	 * 			   x = b; => y = a; => a = 1; => b = 1; 				��ӡ �� ?? �Σ�x=0, y=0
     * @throws InterruptedException 
 	 * 
     */
	public static void testReOrder () throws InterruptedException {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					// �п��ܷ������ţ��� ��ִ�� x = b,��ִ�� a = 1
					a = 1;
					x = b;
				}
			});

			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					// �п��ܷ������ţ�����ִ�� y = a,��ִ�� b = 1;	
					b = 1;
					y = a;
				}
			});

			t1.start();
			t2.start();
			// t1 �̲߳�ӣ������߳���ִ��
			t1.join();
			// t2 �̲߳�ӣ������߳���ִ��
			t2.join();
			/**
			 * ���û��ָ�����ţ�����Ŀ��Խ��Ϊ:(0,1)(1,1)(1,0) ��ʵ�����п��ܻ����(0,0)
			 */
			System.out.println("�� " + i + "�Σ�x=" + x + ", y=" + y);
			if (x == 0 && y == 0) {
				System.out.println("������ָ������");
				break;
			}
			// ȫ�����ó� 0
			a = b = 0;
			x = y = 0;
		}
	}
	
	/**
	 * ��ȫ��Ч�ض�ȡ�����ļ�
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
	 * AtomicInteger ԭ����,�ײ�ʹ�� volitate �ؼ���,���̰߳�ȫ����
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
		// ��ӡ�Ļ��� 1000000 ô��
		System.out.println(safeCount.get());
	}
	
	
	/**
	 * ��ϰ��ʹ�������߳̽����ӡ 123... �� ABC...
	 * 
	 * object��wait()��notify()��notifyAll() ����
	 * 
	 * wait()��notify()��notifyAll()�� Object�� �еķ���
	 * ��������������������������֪�����¼�����Ϣ��
	 * 1��wait()��notify()��notifyAll()�����Ǳ��ط���������Ϊfinal�������޷�����д��
	 * 2������ĳ�������wait()�������õ�ǰ�߳����������ҵ�ǰ�̱߳���ӵ�д˶����monitor��������
	 * 3������ĳ�������notify()�����ܹ�����һ�����ڵȴ���������monitor���̣߳�����ж���̶߳��ڵȴ���������monitor����ֻ�ܻ�������һ���̣߳�
	 * 4������notifyAll()�����ܹ������������ڵȴ���������monitor���̣߳�
	 */
	public static void testPrintABC123 () {

		// ������
		final Object obj = new Object();
		
		Runnable runnable_print123 = new Runnable() {
			
			@Override
			public void run() {
				synchronized (obj) {
					for (int i = 1;; i++) {
						System.out.println(i % 10);
						// ������ obj �󶨵������߳�
						obj.notifyAll();
						try {
							// �����̣߳��ȴ�������
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











