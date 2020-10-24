package com.linkknown.concurrent;

public class VolatileTest {

	public static class RunThread extends Thread {

		private boolean isRunning = true;				// ����			
//		private volatile boolean isRunning = true;

		public boolean isRunning() {
			return isRunning;
		}

		public void setRunning(boolean isRunning) {
			this.isRunning = isRunning;
		}

		@Override
		public void run() {
			System.out.println("���뵽run��������");
			while (isRunning == true) {
				int i = 0;
				i ++;
				
				
				// �ڱ������� volatile �ĵ��������� while ѭ���������һ���ӡ����䣬����ӡʲô���Ͳ��������ѭ��
				/*
				 * ԭ��
				 * ��ӡ����������̵߳����񣨼�main�̣߳�������м����һ���̣߳��൱��һ���߳��������������һ���̣߳������ʱ��
				 * ԭ�����߳�ʱ�䱻���߳̽ӹ��ˣ������ʧ�ܵ�ԭ��������ɱ�Ĵ��룬���������whileѭ���������������㣬�Ͳ������������⡣
				 * 
				 * ִ��System.out.println();��仰ʱ������һ�������Ĺ��̣�Ȼ��ʹ����synchronized�������������������²����� ����
				 * 1.���ͬ���� ����
				 * 2.��չ����ڴ� ����
				 * 3.�����ڴ��п������󸱱��������ڴ� ����
				 * 4.ִ�д��루��ӡ����ӼӲ����� ����
				 * 5.ˢ�����ڴ����� ����
				 * 6.�ͷ�ͬ����
				 */
//				System.out.println("execute run method...");
			}
			System.out.println("�߳�ִ�������");
		}
	}

	public static void main(String[] args) {
//	public static void testVisible () {
		try {
			RunThread thread = new RunThread();
			thread.start();
			Thread.sleep(1000);
			// main �߳̽��������߳� RunThread �еĹ����������Ϊfalse���Ӷ����� RunThread �е� while ѭ������
			// �� volatile ���Σ��ڴ�ɼ��ԣ�while ѭ���ᱻ��ֹ
			thread.setRunning(false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * ��ָ֤�����ţ������ԣ�
	 */
	/*************************************************** ʹ�� volatile �ؼ��ʽ������������ *****************************************/
    volatile static Integer a = 0;								
    volatile static Integer b = 0;
    static Integer x = 0;
    static Integer y = 0;
    
    /**
     *������ʾָ������
     *
     * ָ�����Żᷢ���������׶Σ�
 	 * 1. ������(jvm �����ֽ���ʱ)
 	 * 2. cpu ִ����
 	 * �����ڵ��߳���˵�����ܷ������������ţ������뱣����Դ����һ�µ���������As-If-Serial��.
 	 * ��������֤�˵��̵߳�ִ�н��������Ԥ��һ�£����ڶ��̵߳�������ͻ������Ԥ�ڲ�һ�µ������
 	 * ��������һ���������ԭ������ָ������
 	 * 
 	 * ִ�����������У� a = 1; => x = b; => b = 1; => y = a; 				��ӡ �� ?? �Σ�x=0, y=1
 	 * 			   b = 1; => y = a; => a = 1; => x = b; 				��ӡ �� ?? �Σ�x=1, y=0
 	 * 			   a = 1; => b = 1; => x = b; => y = a; 				��ӡ �� ?? �Σ�x=1, y=1
 	 * 
 	 * ָ�������쳣�����У�
 	 * 			   x = b; => y = a; => a = 1; => b = 1; 				��ӡ �� ?? �Σ�x=0, y=0
     * @throws InterruptedException 
 	 * 
     */
//	public static void main(String[] args) throws InterruptedException {
		public static void testReOrder () throws InterruptedException {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					// �п��ܷ������ţ��� ��ִ�� x = b,��ִ�� a = 1
					a = 1;
					x = b;
				}
			});

			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					// �п��ܷ������ţ�����ִ�� y = a,��ִ�� b = 1;	
					b = 1;
					y = a;
				}
			});

			t1.start();
			t2.start();
			// t1 �̲߳�ӣ������߳���ִ��
			t1.join();
			// t2 �̲߳�ӣ������߳���ִ��
			t2.join();
			/**
			 * ���û��ָ�����ţ�����Ŀ��Խ��Ϊ:(0,1)(1,1)(1,0) ��ʵ�����п��ܻ����(0,0)
			 */
			System.out.println("�� " + i + "�Σ�x=" + x + ", y=" + y);
			if (x == 0 && y == 0) {
				System.out.println("������ָ������");
				break;
			}
			// ȫ�����ó� 0
			a = b = 0;
			x = y = 0;
		}
	}

}
