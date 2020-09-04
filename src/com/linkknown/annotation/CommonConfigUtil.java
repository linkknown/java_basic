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

		// Proxy（库类）中的newProxyInstance方法被调用，该方法返回一个被代理对象的实例，然后向上转型为其对应的接口
		// loader: 一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
		// interfaces: 一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它，
		// 那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了
		// h: 一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
		return (CommonConfig) Proxy.newProxyInstance(CommonConfig.class.getClassLoader(),
				new Class[] { CommonConfig.class }, new ReadConfigHandler(properties));
	}
}
