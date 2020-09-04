package com.linkknown.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
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

	static int count = 0;

	/**
	 * i++ ���̰߳�ȫ���� ����ԭ�Ӳ�������Ϊ������ȡ���ġ���
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
		// ��ӡ�Ļ��� 1000000 ô��
		System.out.println(ThreadTest2.count);

	}

	/**
	 * ���Է�ԭ�Ӳ���
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
	 * ����ԭ�Ӳ���
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
						// add\add2 ������������֤��ԭ�Ӳ���
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
	 * ����ԭ�Ӳ���
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
	 * ���� StringBuffer �̰߳�ȫ��
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
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testList() throws InterruptedException {
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
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testList2() throws InterruptedException {
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

//	/**
//	 * ���� List ���� bug
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
//	 * ��ȫ�ض�ȡ�����ļ�
//	 */
//	public static void main(String[] args) {
//		for (int i=0; i<10; i++) {
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
////					System.out.println(AccountUtil.getInstance());		// û��������������ȫ
////					System.out.println(AccountUtil.getInstance2());		// ����������������ȫ
////					System.out.println(AccountUtil.getInstance3());		// ����������synchronized �ⲿ�пգ�����ȫ�� �ⲿ�пգ�����ȫ,������û�м�����
////					System.out.println(AccountUtil.getInstance4());		// �����������̰߳�ȫ
//					System.out.println(AccountUtil.getInstance5());		// �����������̰߳�ȫ, (��Ԫ���������ܻ��� bug)
//					
//				}
//			}).start();
//		}
//	}
//	
	
	private static AtomicInteger safeCount = new AtomicInteger(0);
	
	/**
	 * AtomicInteger ԭ����,�ײ�ʹ�� volitate �ؼ���,���̰߳�ȫ����
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
		// ��ӡ�Ļ��� 1000000 ô��
		System.out.println(ThreadTest2.safeCount.get());

	}
}
