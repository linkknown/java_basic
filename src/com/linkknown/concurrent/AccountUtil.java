package com.linkknown.concurrent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.linkknown.util.IOUtil;

public class AccountUtil {

	private static Object object = new Object();
	
	/**
	 * 在讲指令重排的时候，讲了一个例子，说 在 new 一个对象的时候，分为3个步骤，
	 * <1> 创建内存，
	 * <2>初始化对象，
	 * <3>对象指针指向创建的内存
	 * 指令优化的时候，有可能会把 <3> 和 <2> 的顺序颠倒
	 */
	private static volatile Account account = null;
	
	/**
	 * 不加锁 initAccount 会执行多次，此方法有 bug
	 * 
	 * @return
	 */
	public static Account getInstance() {
		if (account == null) {
			initAccount();
		}
		return account;
	}

	public synchronized static Account getInstance2() {
		if (account == null) {
			initAccount();
		}
		return account;
	}

	public static Account getInstance3() {
		// 外部判空，并发不安全
		if (account == null) {
			synchronized (object) { // 使用 object 对象作为锁
				initAccount();
			}
		}
		return account;
	}

	public static Account getInstance4() {
		synchronized (AccountUtil.class) {
			// 内部判空，安全
			if (account == null) {
				initAccount();
			}
		}
		return account;
	}
	
//	对于两次instance的是否为空的判断解释：
//	1.为何在synchronization外面的判断？
//	为了提高性能！如果拿掉这次的判断那么在行的时候就会直接的运行synchronization，所以这会使每个getInstance()都会得到一个静态内部锁，
//	这样的话锁的获得以及释放的开销（包括上下文切换，内存同步等）都不可避免，降低了效率。所以在synchronization前面再加一次判断是否为空，
//	则会大大降低synchronization块的执行次数。
//
//	2.为何在synchronization内部还要执行一次呢？
//	因为可能会有多个线程一起进入同步块外的 if，如果在同步块内不进行二次检验的话就会生成多个实例了。
//
//	PS：双重检验情况下，保存实例的唯一的静态变量要用volatile修饰，volatile能禁止指令重排。
	public static Account getInstance5() {
		// 内外同时判空，效率更高
		if (account == null) {
			synchronized (AccountUtil.class) {
				// 内部判空，安全
				if (account == null) {
					initAccount();
				}
			}
		}
		return account;
	}

	private static void initAccount() {
		try {
			System.out.println("execute initAccount method");

			// 先 new 再赋值属性：有线程安全问题
//			account = new Account();
//
//			Properties properties = new Properties();
//			InputStream inputStream = AccountUtil.class.getClassLoader()
//					.getResourceAsStream("com/linkknown/concurrent/account.properties");
//			properties.load(inputStream);
//			account.setUserName(properties.getProperty("userName"));
//			account.setPassword(properties.getProperty("password"));
//			account.setIp(properties.getProperty("ip"));
			
			
			
			
			
			
//			Account account0 = new Account();
//
//			Properties properties = new Properties();
//			InputStream inputStream = AccountUtil.class.getClassLoader()
//					.getResourceAsStream("com/linkknown/concurrent/account.properties");
//			properties.load(inputStream);
//			account0.setUserName(properties.getProperty("userName"));
//			account0.setPassword(properties.getProperty("password"));
//			account0.setIp(properties.getProperty("ip"));
//			
//			// 赋值之前所有属性必须都初始化完成，否则外部会获取到空属性对象
//			// 同时 static 字段要设置成 volatile 来禁止指令重排
//			account = account0;		
			
			
			
			
			
			
			account = new Account();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class Account {
		private String userName;
		private String password;
		private String ip;

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		protected Account() {
			Properties properties = new Properties();
			InputStream inputStream = null;
			try {
				inputStream = AccountUtil.class.getClassLoader()
						.getResourceAsStream("com/linkknown/concurrent/account.properties");
				properties.load(inputStream);
				
				this.setUserName(properties.getProperty("userName"));
				this.setPassword(properties.getProperty("password"));
				this.setIp(properties.getProperty("ip"));
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				IOUtil.close(inputStream);
			}

		}
		
		

		@Override
		public String toString() {
			return "Account [userName=" + userName + ", password=" + password + ", ip=" + ip + "]";
		}

	}
}
