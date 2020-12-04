package com.linkknown.concurrent;

public class ThreadLocalTest {
	
	/**
	 * ThreadLocal ���̱߳��ش洢,��ÿ���߳��ж�������һ�� ThreadLocalMap ����,
	 * ÿ���߳̿��Է����Լ��ڲ� ThreadLocalMap �����ڵ� value.ͨ�����ַ�ʽ,������Դ�ڶ��̼߳乲��
	 */

	public static final ThreadLocal<Integer> THREAD_LOCAL_NUM01 = new ThreadLocal<Integer>() {
		// ����ͨ�� initialValue ������ ThreadLocal �̱߳������ó�ʼֵ
		protected Integer initialValue() {
			return 0;
		};
	};
	
	private static void print() {
		System.out.println(THREAD_LOCAL_NUM01.get());
		
		// ʹ�����֮����Ҫ�����ͷ�,���� ThreadLocalMap ���ܻ� OOM �ڴ����
		THREAD_LOCAL_NUM01.remove();
	}

	private static void add(int i) {
		THREAD_LOCAL_NUM01.set(THREAD_LOCAL_NUM01.get() + i);
	}
	
	// �̱߳�������
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
