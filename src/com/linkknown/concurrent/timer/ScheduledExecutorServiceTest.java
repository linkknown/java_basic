package com.linkknown.concurrent.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {
	
	/**
	 * ScheduledExecutorService,是基于线程池设计的定时任务类,每个调度任务都会分配到线程池中的一个线程去执行,也就是说,任务是并发执行,互不影响
	 * 需要注意,只有当调度任务来的时候,ScheduledExecutorService才会真正启动一个线程,其余时间ScheduledExecutorService都是出于轮询任务的状态
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		
		long initialDelay = 1;
        long period = 1;
        
        // ScheduleAtFixedRate 是基于固定时间间隔进行任务调度, 
        // ScheduleWithFixedDelay 取决于每次任务执行的时间长短,是基于不固定时间间隔进行任务调度
        
        // ScheduleAtFixedRate 每次执行时间为上一次任务开始起向后推一个时间间隔,
        // 即每次执行时间为 :initialDelay, initialDelay+period, initialDelay+2*period, ...
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
		
		// ScheduleWithFixedDelay 每次执行时间为上一次任务结束起向后推一个时间间隔,
		// 即每次执行时间为:initialDelay, initialDelay+executeTime+delay, initialDelay+2*executeTime+2*delay
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
