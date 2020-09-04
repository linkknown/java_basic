package com.linkknown.enum0;

/**
 * ����ö����,�вι�����
 * 
 * @author Administrator
 *
 */
public enum SeasonEnum2 {

	SPRING("����","��������"),
	SUMMER("����","��������"),
	AUTUMN("����","��������"),
	WINTER("����","��������");	// �����вι�����
	
	private final String seasonName;
	private final String seasonDesc;

	private SeasonEnum2(String seasonName, String seasonDesc) {
		this.seasonName = seasonName;
		this.seasonDesc = seasonDesc;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public String getSeasonDesc() {
		return seasonDesc;
	}

}
