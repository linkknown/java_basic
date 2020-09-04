package com.linkknown.io;

import java.io.Serializable;

public class Address implements Serializable {

	/**
	 * 提供了一个序列版本号
	 */
	private static final long serialVersionUID = -1193449394540803318L;

	// 省
	private String province;
	// 市
	private String city;
	// 县
	private String county;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@Override
	public String toString() {
		return "Address [province=" + province + ", city=" + city + ", county=" + county + "]";
	}
	
}
