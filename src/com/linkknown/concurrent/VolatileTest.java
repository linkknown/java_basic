package com.linkknown.concurrent;

public class VolatileTest {

	public static class RunThread extends Thread {

		private boolean isRunning = true;				// 错误			
//		private volatile boolean isRunning = true;

		public boolean isRunning() {
			return isRunning;
		}

		public void setRunning(boolean isRunning) {
			this.isRunning = isRunning;
		}

		@Override
		public void run() {
			System.out.println("进入到run方法中了");
			while (isRunning == true) {
				int i = 0;
				i ++;
				
				
				// 在变量不加 volatile 的的情况，如果 while 循环里面加上一句打印的语句，随便打印什么，就不会产生死循环
				/*
				 * 原因：
				 * 打印语句属于主线程的任务（即main线程），如果中间加了一个线程，相当于一个线程里面加入了另外一个线程，加入的时候，
				 * 原来的线程时间被主线程接管了，这就是失败的原因，如果换成别的代码，比如随便在while循环里面做自增运算，就不会存在这个问题。
				 * 
				 * 执行System.out.println();这句话时，会有一个上锁的过程，然后使用了synchronized上锁这个操作后会做以下操作： 　　
				 * 1.获得同步锁 　　
				 * 2.清空工作内存 　　
				 * 3.从主内存中拷贝对象副本到本地内存 　　
				 * 4.执行代码（打印语句或加加操作） 　　
				 * 5.刷新主内存数据 　　
				 * 6.释放同步锁
				 */
//				System.out.println("execute run method...");
			}
			System.out.println("线程执行完成了");
		}
	}

	public static void main(String[] args) {
//	public static void testVisible () {
		try {
			RunThread thread = new RunThread();
			thread.start();
			Thread.sleep(1000);
			// main 线程将启动的线程 RunThread 中的共享变量设置为false，从而想让 RunThread 中的 while 循环结束
			// 加 volatile 修饰，内存可见性，while 循环会被终止
			thread.setRunning(false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 验证指令重排（有序性）
	 */
	/*************************************************** 使用 volatile 关键词解决有序性问题 *****************************************/
    volatile static Integer a = 0;								
    volatile static Integer b = 0;
    static Integer x = 0;
    static Integer y = 0;
    
    /**
     *用来演示指令重排
     *
     * 指令重排会发生在两个阶段：
 	 * 1. 编译期(jvm 加载字节码时)
 	 * 2. cpu 执行期
 	 * 但对于单线程来说，不管发生怎样的重排，都必须保持与源代码一致的输出结果（As-If-Serial）.
 	 * 上述规则保证了单线程的执行结果总是与预期一致，但在多线程的情况，就会出现与预期不一致的情况，
 	 * 而导致这一情况发生的原因，正是指令重排
 	 * 
 	 * 执行正常场景有： a = 1; => x = b; => b = 1; => y = a; 				打印 第 ?? 次，x=0, y=1
 	 * 			   b = 1; => y = a; => a = 1; => x = b; 				打印 第 ?? 次，x=1, y=0
 	 * 			   a = 1; => b = 1; => x = b; => y = a; 				打印 第 ?? 次，x=1, y=1
 	 * 
 	 * 指令重排异常场景有：
 	 * 			   x = b; => y = a; => a = 1; => b = 1; 				打印 第 ?? 次，x=0, y=0
     * @throws InterruptedException 
 	 * 
     */
//	public static void main(String[] args) throws InterruptedException {
		public static void testReOrder () throws InterruptedException {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					// 有可能发生重排，即 先执行 x = b,再执行 a = 1
					a = 1;
					x = b;
				}
			});

			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					// 有可能发生重排，即先执行 y = a,再执行 b = 1;	
					b = 1;
					y = a;
				}
			});

			t1.start();
			t2.start();
			// t1 线程插队，比主线程先执行
			t1.join();
			// t2 线程插队，比主线程先执行
			t2.join();
			/**
			 * 如果没有指令重排，输出的可以结果为:(0,1)(1,1)(1,0) 但实际上有可能会输出(0,0)
			 */
			System.out.println("第 " + i + "次，x=" + x + ", y=" + y);
			if (x == 0 && y == 0) {
				System.out.println("发生了指令重排");
				break;
			}
			// 全部重置成 0
			a = b = 0;
			x = y = 0;
		}
	}

}
