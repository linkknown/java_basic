package com.linkknown.concurrent.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {
	
	/**
	 * ScheduledExecutorService,�ǻ����̳߳���ƵĶ�ʱ������,ÿ���������񶼻���䵽�̳߳��е�һ���߳�ȥִ��,Ҳ����˵,�����ǲ���ִ��,����Ӱ��
	 * ��Ҫע��,ֻ�е�������������ʱ��,ScheduledExecutorService�Ż���������һ���߳�,����ʱ��ScheduledExecutorService���ǳ�����ѯ�����״̬
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		
		long initialDelay = 0;
        long period = 1;
        
        // ScheduleAtFixedRate �ǻ��ڹ̶�ʱ���������������, 
        // ScheduleWithFixedDelay ȡ����ÿ������ִ�е�ʱ�䳤��,�ǻ��ڲ��̶�ʱ���������������
        
        // ScheduleAtFixedRate ÿ��ִ��ʱ��Ϊ��һ������ʼ�������һ��ʱ����,
        // ��ÿ��ִ��ʱ��Ϊ :initialDelay, initialDelay+period, initialDelay+2*period, ...
		testScheduled01(sdf, service, initialDelay, period); 
		
		// ScheduleWithFixedDelay ÿ��ִ��ʱ��Ϊ��һ����������������һ��ʱ����,
		// ��ÿ��ִ��ʱ��Ϊ:initialDelay, initialDelay+executeTime+delay, initialDelay+2*executeTime+2*delay
//		testScheduled02(sdf, service, initialDelay, period);
	}

	private static void testScheduled01(SimpleDateFormat sdf, ScheduledExecutorService service, long initialDelay,
			long period) {
		service.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				
				// �쳣��Ҫ�Լ�������
//				try {
//					if (new Random().nextInt(100) % 2 == 0) {
//						System.out.println("�����쳣��...");
//						throw new RuntimeException("�����쳣��...");
//					}
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
				
				
				System.out.println("task1 is running...." + sdf.format(new Date()));
				try {
					TimeUnit.SECONDS.sleep(1);
					// If any execution of this task takes longer than its period, then subsequent executions may start late, but will not concurrently execute.
					// ���ִ�д�������������ڳ�,Ȼ���Ǻ�����ִ��,�����ӳ�����,������ͬʱִ��
					// ��ʵ�����ֶ�ʱ��������Ҫ���� Quartz ������Ϣ������ʵ��
//					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, initialDelay, period, TimeUnit.SECONDS);
	}

	private static void testScheduled02(SimpleDateFormat sdf, ScheduledExecutorService service, long initialDelay,
			long period) {
		service.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(">> task2 is running...." + sdf.format(new Date()));
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, initialDelay, period, TimeUnit.SECONDS);
	}
}
