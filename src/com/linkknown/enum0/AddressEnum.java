package com.linkknown.enum0;

/**
 * 定义一个地址枚举类
 * 
 * @author Administrator
 *
 */
public enum AddressEnum {
	
	/**
	 * 调用构造器
	 */
	FEIXI("安徽省","合肥市","肥西县"),
	FEIDONG("安徽省","合肥市","肥东县");
	
	// final :枚举类属性一旦确定,就不能再修改
	private final String province;
	private final String city;
	private final String country;
	
	// 构造器进行实例化,枚举类构造器必须是私有的
	private AddressEnum(String province, String city, String country) {
		this.province = province;
		this.city = city;
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}
	
}
