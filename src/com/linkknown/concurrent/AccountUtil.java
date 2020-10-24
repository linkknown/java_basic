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
	 * �ڽ�ָ�����ŵ�ʱ�򣬽���һ�����ӣ�˵ �� new һ�������ʱ�򣬷�Ϊ3�����裬
	 * <1> �����ڴ棬
	 * <2>��ʼ������
	 * <3>����ָ��ָ�򴴽����ڴ�
	 * ָ���Ż���ʱ���п��ܻ�� <3> �� <2> ��˳��ߵ�
	 */
	private static volatile Account account = null;
	
	/**
	 * ������ initAccount ��ִ�ж�Σ��˷����� bug
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
		// �ⲿ�пգ���������ȫ
		if (account == null) {
			synchronized (object) { // ʹ�� object ������Ϊ��
				initAccount();
			}
		}
		return account;
	}

	public static Account getInstance4() {
		synchronized (AccountUtil.class) {
			// �ڲ��пգ���ȫ
			if (account == null) {
				initAccount();
			}
		}
		return account;
	}
	
//	��������instance���Ƿ�Ϊ�յ��жϽ��ͣ�
//	1.Ϊ����synchronization������жϣ�
//	Ϊ��������ܣ�����õ���ε��ж���ô���е�ʱ��ͻ�ֱ�ӵ�����synchronization���������ʹÿ��getInstance()����õ�һ����̬�ڲ�����
//	�����Ļ����Ļ���Լ��ͷŵĿ����������������л����ڴ�ͬ���ȣ������ɱ��⣬������Ч�ʡ�������synchronizationǰ���ټ�һ���ж��Ƿ�Ϊ�գ�
//	����󽵵�synchronization���ִ�д�����
//
//	2.Ϊ����synchronization�ڲ���Ҫִ��һ���أ�
//	��Ϊ���ܻ��ж���߳�һ�����ͬ������� if�������ͬ�����ڲ����ж��μ���Ļ��ͻ����ɶ��ʵ���ˡ�
//
//	PS��˫�ؼ�������£�����ʵ����Ψһ�ľ�̬����Ҫ��volatile���Σ�volatile�ܽ�ָֹ�����š�
	public static Account getInstance5() {
		// ����ͬʱ�пգ�Ч�ʸ���
		if (account == null) {
			synchronized (AccountUtil.class) {
				// �ڲ��пգ���ȫ
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

			// �� new �ٸ�ֵ���ԣ����̰߳�ȫ����
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
//			// ��ֵ֮ǰ�������Ա��붼��ʼ����ɣ������ⲿ���ȡ�������Զ���
//			// ͬʱ static �ֶ�Ҫ���ó� volatile ����ָֹ������
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
