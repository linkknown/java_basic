package com.linkknown.concurrent;

public class ThreadLocalTest {
	
	/**
	 * ThreadLocal ���̱߳��ش洢,��ÿ���߳��ж�������һ�� ThreadLocalMap ����,
	 * ÿ���߳̿��Է����Լ��ڲ� ThreadLocalMap �����ڵ� value.ͨ�����ַ�ʽ,������Դ�ڶ��̼߳乲��
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

	// �����ó�ʼֵ�õ��Ļ��� null
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
	
	// ����ͨ�� initialValue ������ ThreadLocal �̱߳������ó�ʼֵ
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
	
	
	// �̱߳�������
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
		
		// ʹ�����֮����Ҫ�����ͷ�,���� ThreadLocalMap ���ܻ� OOM �ڴ����
		THREAD_LOCAL_NUM02.remove();
	}

	private static void add() {
		THREAD_LOCAL_NUM02.set(THREAD_LOCAL_NUM02.get() + 1);
	}
	
}
