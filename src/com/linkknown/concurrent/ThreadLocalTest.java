package com.linkknown.concurrent;

public class ThreadLocalTest {
	
	/**
	 * ThreadLocal 是线程本地存储,在每个线程中都创建了一个 ThreadLocalMap 对象,
	 * 每个线程可以访问自己内部 ThreadLocalMap 对象内的 value.通过这种方式,避免资源在多线程间共享
	 */
	public static final ThreadLocal<Integer> THREAD_LOCAL_NUM01 = new ThreadLocal<Integer>();

	public static final ThreadLocal<Integer> THREAD_LOCAL_NUM02 = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;
		};
	};

	public static void main(String[] args) {

//		testThreadLocal01();
//		testThreadLocal02();
		testThreadLocal03();

	}

	// 不设置初始值拿到的会是 null
	private static void testThreadLocal01() {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println(THREAD_LOCAL_NUM01.get());
				}
			}).start();
		}
	}
	
	// 可以通过 initialValue 方法给 ThreadLocal 线程变量设置初始值
	private static void testThreadLocal02() {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println(THREAD_LOCAL_NUM02.get());
				}
			}).start();
		}
	}
	
	
	// 线程变量隔离
	private static void testThreadLocal03() {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
//					THREAD_LOCAL_NUM02.set(THREAD_LOCAL_NUM02.get() + 1);
//					
//					System.out.println(THREAD_LOCAL_NUM02.get());
					
					add();
					
					print();
				}
			}).start();
		}
	}
	
	private static void print() {
		System.out.println(THREAD_LOCAL_NUM02.get());
		
		// 使用完成之后需要进行释放,否则 ThreadLocalMap 可能会 OOM 内存溢出
		THREAD_LOCAL_NUM02.remove();
	}

	private static void add() {
		THREAD_LOCAL_NUM02.set(THREAD_LOCAL_NUM02.get() + 1);
	}
	
}
