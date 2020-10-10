package com.linkknown.concurrent.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 根据是否循环执行分为两类：
//
//	// 只执行一次
//	public void schedule(TimerTask task, long delay);
//	public void schedule(TimerTask task, Date time);
//	
//	// 循环执行
//	// 在循环执行类别中根据循环时间间隔又可以分为两类
//	public void schedule(TimerTask task, long delay, long period)						前一次执行程序结束后 xxx ms 后开始执行下一次程序,重复执行
//	public void schedule(TimerTask task, Date firstTime, long period)
//	
//	
//	public void scheduleAtFixedRate(TimerTask task, long delay, long period)			前一次程序执行开始后 xxx ms 后开始执行下一次程序,重复执行
//	public void scheduleAtFixedRate(TimerTask task, Date firstTime, long period)
//        
 * @author Administrator
 *
 */
public class TimerTest {

	public static void main(String[] args) {
//		testTimer01 ();
//		testTimer02 ();
//		testTimer03 ();
//		testTimer04 ();
	}
	
	// 延迟 0 s 执行 1 次
	public static void testTimer01() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("当前时间 == " + this.scheduledExecutionTime());
			}
		}, 0);
	}
	
	// 延迟 10 s 执行 1 次
	public static void testTimer02() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("当前时间 == " + this.scheduledExecutionTime());
			}
		}, 10000);
	}
	
	// 前一次执行程序结束后 1000ms 后开始执行下一次程序,重复执行
	public static void testTimer03() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("当前时间 == " + this.scheduledExecutionTime());
			}
		}, 0, 1000);
	}
	
	public static void testTimer04() {
		Timer timer = new Timer();
		// 前一次程序执行开始后 1000ms 后开始执行下一次程序,重复执行
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("当前时间 == " + this.scheduledExecutionTime());
			}
		}, 0, 1000);
	}
	
}
