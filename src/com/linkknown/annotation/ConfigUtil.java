package com.linkknown.annotation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Properties;

import com.linkknown.annotation.ConfigUtil.Config;

public class ConfigUtil {
	
	private static volatile Config config = null;
	
	public static Config getInstance () {		
		if (config == null) {
			synchronized (ConfigUtil.class) {
				if (config == null) {
					// Proxy�����ࣩ�е�newProxyInstance���������ã��÷�������һ������������ʵ����Ȼ������ת��Ϊ���Ӧ�Ľӿ�
					// loader: һ��ClassLoader���󣬶��������ĸ�ClassLoader�����������ɵĴ��������м���
					// interfaces: һ��Interface��������飬��ʾ�����ҽ�Ҫ������Ҫ����Ķ����ṩһ��ʲô�ӿڣ�������ṩ��һ��ӿڸ�����
					// ��ô���������������ʵ���˸ýӿ�(��̬)�������Ҿ��ܵ�������ӿ��еķ�����
					// h: һ��InvocationHandler���󣬱�ʾ���ǵ��������̬��������ڵ��÷�����ʱ�򣬻��������һ��InvocationHandler������
					config = (Config) Proxy.newProxyInstance(ConfigUtil.class.getClassLoader(), new Class[] {Config.class}, new ReadConfigHandler());
				}
			}
		}
		return config;
	}
	
	public static interface Config {
		
		@ReadConfig(value = "userName")
		String getUserName();
		
		@ReadConfig(value = "password")
		String getPassWord();
		
	}
}
