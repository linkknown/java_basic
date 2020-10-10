package com.linkknown.concurrent.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * �����Ƿ�ѭ��ִ�з�Ϊ���ࣺ
//
//	// ִֻ��һ��
//	public void schedule(TimerTask task, long delay);
//	public void schedule(TimerTask task, Date time);
//	
//	// ѭ��ִ��
//	// ��ѭ��ִ������и���ѭ��ʱ�����ֿ��Է�Ϊ����
//	public void schedule(TimerTask task, long delay, long period)						ǰһ��ִ�г�������� xxx ms ��ʼִ����һ�γ���,�ظ�ִ��
//	public void schedule(TimerTask task, Date firstTime, long period)
//	
//	
//	public void scheduleAtFixedRate(TimerTask task, long delay, long period)			ǰһ�γ���ִ�п�ʼ�� xxx ms ��ʼִ����һ�γ���,�ظ�ִ��
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
	
	// �ӳ� 0 s ִ�� 1 ��
	public static void testTimer01() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("��ǰʱ�� == " + this.scheduledExecutionTime());
			}
		}, 0);
	}
	
	// �ӳ� 10 s ִ�� 1 ��
	public static void testTimer02() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("��ǰʱ�� == " + this.scheduledExecutionTime());
			}
		}, 10000);
	}
	
	// ǰһ��ִ�г�������� 1000ms ��ʼִ����һ�γ���,�ظ�ִ��
	public static void testTimer03() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("��ǰʱ�� == " + this.scheduledExecutionTime());
			}
		}, 0, 1000);
	}
	
	public static void testTimer04() {
		Timer timer = new Timer();
		// ǰһ�γ���ִ�п�ʼ�� 1000ms ��ʼִ����һ�γ���,�ظ�ִ��
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("��ǰʱ�� == " + this.scheduledExecutionTime());
			}
		}, 0, 1000);
	}
	
}
