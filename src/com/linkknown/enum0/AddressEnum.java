package com.linkknown.enum0;

/**
 * ����һ����ַö����
 * 
 * @author Administrator
 *
 */
public enum AddressEnum {
	
	/**
	 * ���ù�����
	 */
	FEIXI("����ʡ","�Ϸ���","������"),
	FEIDONG("����ʡ","�Ϸ���","�ʶ���");
	
	// final :ö��������һ��ȷ��,�Ͳ������޸�
	private final String province;
	private final String city;
	private final String country;
	
	// ����������ʵ����,ö���๹����������˽�е�
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
