package com.linkknown.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

/**
 * �̲߳���
 * 
 * junit ��֧�ֶ��̲߳���,�����֧�����̲߳��ԵĻ�����Ҫʹ�� Thread.currentThread().sleep(xxx) �������������߳�
 * 
 * @author Administrator
 *
 */
public class ThreadTest1 {
	
	public static void main(String[] args) throws Exception {
//		testThreadName();
//		testThreadName2();
//		testThreadName3();
//		testPriority();
//		testCountDownLatch();
		testCountDownLatch2();
	}

	/**
	 * ��ȡ�߳���
	 */
	public static void testThreadName() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println("����ִ���̣߳�" + i);
				}
				System.out.println("��ǰ�߳������ǣ�" + Thread.currentThread().getName());
			}
		};
		thread.start();
		System.out.println("��ǰ�߳������ǣ�" + Thread.currentThread().getName());
	}

	/**
	 * �޸��߳�����
	 */
	public static void testThreadName2() {
		Thread.currentThread().setName("Thread___main");
		Thread thread = new Thread() {
			@Override
			public void run() {
				Thread.currentThread().setName("Thread___01");
				System.out.println("��ǰ�߳������ǣ�" + Thread.currentThread().getName());
			}
		};
		thread.start();
		System.out.println("��ǰ�߳������ǣ�" + Thread.currentThread().getName());
	}

	/**
	 * �޸��߳�����
	 */
	public static void testThreadName3() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				System.out.println("��ǰ�߳������ǣ�" + Thread.currentThread().getName());
			}
		};
		Thread t1 = new Thread(runnable, "�߳�1");
		Thread t2 = new Thread(runnable, "�߳�2");
		Thread t3 = new Thread(runnable, "�߳�3");

		t1.start();
		t2.start();
		t3.start();
	}

	/**
	 * �����߳����ȼ������ȼ��� = ����ִ�еĸ��ʸߣ�
	 * 
	 * @throws InterruptedException
	 */
	public static void testPriority() throws InterruptedException {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100000; i++) {
					System.out.println(Thread.currentThread().getName() + " ���ȼ� " + Thread.currentThread().getPriority()
							+ " ִ�� " + i);
				}

			}
		};
		Thread t1 = new Thread(runnable, "�߳�1");
		Thread t2 = new Thread(runnable, "�߳�2");
		Thread t3 = new Thread(runnable, "�߳�3");

		t1.setPriority(5);
		t2.setPriority(7);
		t3.setPriority(10);

		t1.start();
		t2.start();
		t3.start();
	}


    // ���� CountDownLatch �� egg:������������Ϊ�ϰ�ɻ����ϰ���һ��ϰ�ߣ�
	// ���ǵ��������˰�һ��Ļ�����˵�ʱ��������������й������ɵĻ���ϰ�����ʱ��������һ���������õ�ʱ��.
    public static void testCountDownLatch () throws InterruptedException {
        long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        
        Runnable runnable = new Runnable() {      
            @Override
            public void run() {
                long _startTime =  System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + " ��ʼ������~");
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));		 // ģ�⹤�˸ɻ��ʱ
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " ��ɹ�����~");
                long _endTime =  System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + "һ������" + (_endTime - _startTime) + "ms");
            }
        };
        Thread worker1 = new Thread(runnable, "����1");
        Thread worker2 = new Thread(runnable, "����2");
        Thread worker3 = new Thread(runnable, "����3");
        worker1.start();
        worker2.start();
        worker3.start();
    
        System.out.println("�ϰ����ڵȴ���������ɹ���...");
        countDownLatch.await();
        System.out.println("������ɹ���,�ϰ忪ʼ���...");
        
        long endTime = System.currentTimeMillis();
        System.out.println("�ϰ�һ������" + (endTime - startTime) + "ms");        
    }

	
	/**
	 * ���� CountDownLatch �� egg:
	 * ������������Ϊ�ϰ�ɻ����ϰ���һ��ϰ�ߣ����ǵ��������˰�һ��Ļ�����˵�ʱ��������������й������ɵĻ
	 * ��ס�������������������ȫ�������ϰ�ż�顣������������Java������������࣬Worker�����ˣ�Boss�����ϰ壬����Ĵ���ʵ������
	 */
	public static void testCountDownLatch2 () {

		CountDownLatch countDownLatch = new CountDownLatch(3);
		Worker worker1 = new Worker("����1", countDownLatch);
		Worker worker2 = new Worker("����2", countDownLatch);
		Worker worker3 = new Worker("����3", countDownLatch);
		Boss boss = new Boss("�ϰ�", countDownLatch);
		new Thread(worker1).start();
		new Thread(worker2).start();
		new Thread(worker3).start();
		new Thread(boss).start();
	}
}
