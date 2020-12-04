package com.linkknown.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

/**
 * 线程测试
 * 
 * junit 不支持多线程测试,如果想支持主线程测试的话，需要使用 Thread.currentThread().sleep(xxx) 来主动阻塞主线程
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
	 * 获取线程名
	 */
	public static void testThreadName() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println("正在执行线程：" + i);
				}
				System.out.println("当前线程名称是：" + Thread.currentThread().getName());
			}
		};
		thread.start();
		System.out.println("当前线程名称是：" + Thread.currentThread().getName());
	}

	/**
	 * 修改线程名称
	 */
	public static void testThreadName2() {
		Thread.currentThread().setName("Thread___main");
		Thread thread = new Thread() {
			@Override
			public void run() {
				Thread.currentThread().setName("Thread___01");
				System.out.println("当前线程名称是：" + Thread.currentThread().getName());
			}
		};
		thread.start();
		System.out.println("当前线程名称是：" + Thread.currentThread().getName());
	}

	/**
	 * 修改线程名称
	 */
	public static void testThreadName3() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				System.out.println("当前线程名称是：" + Thread.currentThread().getName());
			}
		};
		Thread t1 = new Thread(runnable, "线程1");
		Thread t2 = new Thread(runnable, "线程2");
		Thread t3 = new Thread(runnable, "线程3");

		t1.start();
		t2.start();
		t3.start();
	}

	/**
	 * 测试线程优先级（优先级高 = 优先执行的概率高）
	 * 
	 * @throws InterruptedException
	 */
	public static void testPriority() throws InterruptedException {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100000; i++) {
					System.out.println(Thread.currentThread().getName() + " 优先级 " + Thread.currentThread().getPriority()
							+ " 执行 " + i);
				}

			}
		};
		Thread t1 = new Thread(runnable, "线程1");
		Thread t2 = new Thread(runnable, "线程2");
		Thread t3 = new Thread(runnable, "线程3");

		t1.setPriority(5);
		t2.setPriority(7);
		t3.setPriority(10);

		t1.start();
		t2.start();
		t3.start();
	}


    // 测试 CountDownLatch 类 egg:有三个工人在为老板干活，这个老板有一个习惯，
	// 就是当三个工人把一天的活都干完了的时候，他就来检查所有工人所干的活。则老板所用时间就是最后一个工人所用的时间.
    public static void testCountDownLatch () throws InterruptedException {
        long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        
        Runnable runnable = new Runnable() {      
            @Override
            public void run() {
                long _startTime =  System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + " 开始工作啦~");
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));		 // 模拟工人干活耗时
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " 完成工作啦~");
                long _endTime =  System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + "一共消耗" + (_endTime - _startTime) + "ms");
            }
        };
        Thread worker1 = new Thread(runnable, "工人1");
        Thread worker2 = new Thread(runnable, "工人2");
        Thread worker3 = new Thread(runnable, "工人3");
        worker1.start();
        worker2.start();
        worker3.start();
    
        System.out.println("老板正在等待所有人完成工作...");
        countDownLatch.await();
        System.out.println("工人完成工作,老板开始检查...");
        
        long endTime = System.currentTimeMillis();
        System.out.println("老板一共消耗" + (endTime - startTime) + "ms");        
    }

	
	/**
	 * 测试 CountDownLatch 类 egg:
	 * 有三个工人在为老板干活，这个老板有一个习惯，就是当三个工人把一天的活都干完了的时候，他就来检查所有工人所干的活。
	 * 记住这个条件：三个工人先全部干完活，老板才检查。所以在这里用Java代码设计两个类，Worker代表工人，Boss代表老板，具体的代码实现如下
	 */
	public static void testCountDownLatch2 () {

		CountDownLatch countDownLatch = new CountDownLatch(3);
		Worker worker1 = new Worker("工人1", countDownLatch);
		Worker worker2 = new Worker("工人2", countDownLatch);
		Worker worker3 = new Worker("工人3", countDownLatch);
		Boss boss = new Boss("老板", countDownLatch);
		new Thread(worker1).start();
		new Thread(worker2).start();
		new Thread(worker3).start();
		new Thread(boss).start();
	}
}
