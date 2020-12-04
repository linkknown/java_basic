package com.linkknown.concurrent;

public class ThreadLocalTest {
	
	/**
	 * ThreadLocal 是线程本地存储,在每个线程中都创建了一个 ThreadLocalMap 对象,
	 * 每个线程可以访问自己内部 ThreadLocalMap 对象内的 value.通过这种方式,避免资源在多线程间共享
	 */

	public static final ThreadLocal<Integer> THREAD_LOCAL_NUM01 = new ThreadLocal<Integer>() {
		// 可以通过 initialValue 方法给 ThreadLocal 线程变量设置初始值
		protected Integer initialValue() {
			return 0;
		};
	};
	
	private static void print() {
		System.out.println(THREAD_LOCAL_NUM01.get());
		
		// 使用完成之后需要进行释放,否则 ThreadLocalMap 可能会 OOM 内存溢出
		THREAD_LOCAL_NUM01.remove();
	}

	private static void add(int i) {
		THREAD_LOCAL_NUM01.set(THREAD_LOCAL_NUM01.get() + i);
	}
	
	// 线程变量隔离
	private static void testThreadLocal() {
		for (int i = 0; i < 10; i++) {
			final int j = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					add(j);
					
					print();
				}
			}).start();
		}
	}
	
	public static void main(String[] args) {

		testThreadLocal();
	}
	
}
