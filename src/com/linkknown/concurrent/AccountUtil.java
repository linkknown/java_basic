package com.linkknown.concurrent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountUtil {

	private static Object object = new Object();
	private static Account account = null;
	
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
		// �ⲿ�пգ�����ȫ,������û�м���
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

			account = new Account();

			Properties properties = new Properties();
			InputStream inputStream = AccountUtil.class.getClassLoader()
					.getResourceAsStream("com/linkknown/concurrent/account.properties");
			properties.load(inputStream);
			account.setUserName(properties.getProperty("userName"));
			account.setPassword(properties.getProperty("password"));
			account.setIp(properties.getProperty("ip"));
		} catch (IOException e) {
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

		}

		@Override
		public String toString() {
			return "Account [userName=" + userName + ", password=" + password + ", ip=" + ip + "]";
		}

	}
}
