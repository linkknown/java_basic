package com.linkknown.concurrent.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
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
		
		long initialDelay = 1;
        long period = 1;
        
        // ScheduleAtFixedRate �ǻ��ڹ̶�ʱ���������������, 
        // ScheduleWithFixedDelay ȡ����ÿ������ִ�е�ʱ�䳤��,�ǻ��ڲ��̶�ʱ���������������
        
        // ScheduleAtFixedRate ÿ��ִ��ʱ��Ϊ��һ������ʼ�������һ��ʱ����,
        // ��ÿ��ִ��ʱ��Ϊ :initialDelay, initialDelay+period, initialDelay+2*period, ...
		service.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("task1 is running...." + sdf.format(new Date()));
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, initialDelay, period, TimeUnit.SECONDS); 
		
		// ScheduleWithFixedDelay ÿ��ִ��ʱ��Ϊ��һ����������������һ��ʱ����,
		// ��ÿ��ִ��ʱ��Ϊ:initialDelay, initialDelay+executeTime+delay, initialDelay+2*executeTime+2*delay
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
