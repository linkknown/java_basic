package com.linkknown.oop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFile {

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
		InputStream inputStream = ConfigFile.class.getClassLoader()
				.getResourceAsStream("com/linkknown/oop/account.properties");
		properties = new Properties();
		try {
			ConfigFile.properties.load(inputStream);

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
	public static ConfigFile getInstance () {
		
		ConfigFile configUtil = new ConfigFile();
		configUtil.setUserName(properties.getProperty("userName"));
		configUtil.setPassword(ConfigFile.properties.getProperty("password"));
		return configUtil;
	}

}
