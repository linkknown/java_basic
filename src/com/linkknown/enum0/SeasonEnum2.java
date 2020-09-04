package com.linkknown.enum0;

/**
 * 季节枚举类,有参构造器
 * 
 * @author Administrator
 *
 */
public enum SeasonEnum2 {

	SPRING("春天","春天来了"),
	SUMMER("夏天","夏天来了"),
	AUTUMN("秋天","秋天来了"),
	WINTER("冬天","冬天来了");	// 调用有参构造器
	
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
