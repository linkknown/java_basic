package com.linkknown.annotation;

public interface CommonConfig {

	@ReadConfig(value = "userName")
	String getUserName();
	
	@ReadConfig(value = "password")
	String getPassWd();
}
