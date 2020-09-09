package com.linkknown.oop;

/**
 * 定义一个地址类
 * 
 * @author Administrator
 *
 */
public class Address {
	
	/**
	 * 省,设置初始值
	 */
	private String province = "广东省";
	/**
	 * 市
	 */
	private String city;
	/**
	 * 县
	 */
	private String country;

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Address(String province, String city, String country) {
		super();
		this.province = province;
		this.city = city;
		this.country = country;
	}

	public Address() {
		super();
	}

	public Address(String province) {
		super();
		this.province = province;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		return true;
	}

	/**
	 * 这些都是类的方法
	 */
	@Override
	public String toString() {
		return "Address [province=" + province + ", city=" + city + ", country=" + country + "]";
	}

	/**
	 * 这是一个函数
	 */
	public static Address getAddress(String province, String city, String country) {
		Address address = new Address(province, city, country);
		return address;
	}
	
	public static Address[] generateRandomAddress (int count) {
		Address[] addresses = new Address[count];
		for (int i=0; i<count; i++) {
			addresses[i] = getAddress("province" + i, "city" + i, "country" + i);
		}
		return addresses;
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		
		System.out.println("销毁了一个 Address 对象~~");
	}
}
