package com.linkknown.annotation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Properties;

public class CommonConfigUtil {

	public static CommonConfig getInstance() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(
				"E:\\teacher\\code\\eclipse_workspace\\linkknown\\src\\com\\linkknown\\annotation\\common_config.properties"));

		// Proxy�����ࣩ�е�newProxyInstance���������ã��÷�������һ������������ʵ����Ȼ������ת��Ϊ���Ӧ�Ľӿ�
		// loader: һ��ClassLoader���󣬶��������ĸ�ClassLoader�����������ɵĴ��������м���
		// interfaces: һ��Interface��������飬��ʾ�����ҽ�Ҫ������Ҫ����Ķ����ṩһ��ʲô�ӿڣ�������ṩ��һ��ӿڸ�����
		// ��ô���������������ʵ���˸ýӿ�(��̬)�������Ҿ��ܵ�������ӿ��еķ�����
		// h: һ��InvocationHandler���󣬱�ʾ���ǵ��������̬��������ڵ��÷�����ʱ�򣬻��������һ��InvocationHandler������
		return (CommonConfig) Proxy.newProxyInstance(CommonConfig.class.getClassLoader(),
				new Class[] { CommonConfig.class }, new ReadConfigHandler(properties));
	}
}
