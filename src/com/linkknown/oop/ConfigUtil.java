package com.linkknown.oop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

	private String userName;
	private String password;

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

	// ¾²Ì¬ÊôÐÔ
	private static Properties properties;
	// ¾²Ì¬´úÂë¿é
	static {
		InputStream inputStream = ConfigUtil.class.getClassLoader()
				.getResourceAsStream("com/linkknown/oop/account.properties");
		properties = new Properties();
		try {
			ConfigUtil.properties.load(inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// ¾²Ì¬·½·¨
	public static ConfigUtil getInstance () {
		
		ConfigUtil configUtil = new ConfigUtil();
		configUtil.setUserName(properties.getProperty("userName"));
		configUtil.setPassword(ConfigUtil.properties.getProperty("password"));
		return configUtil;
	}

}
