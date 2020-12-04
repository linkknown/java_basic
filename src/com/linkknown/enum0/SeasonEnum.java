package com.linkknown.enum0;

/**
 * 季节枚举类,无参构造器
 */
public enum SeasonEnum {
	// 每个枚举都是通过 Class 在内部实现的，且所有的枚举值都是 public static final 的，等价于
	// public class SeasonEnum {
	// public static final SeasonEnum SPRING = new SeasonEnum();
	// public static final SeasonEnum SUMMER = new SeasonEnum();
	// public static final SeasonEnum AUTUMN = new SeasonEnum();
	// public static final SeasonEnum WINTER = new SeasonEnum();
	// }
	SPRING, SUMMER, AUTUMN, WINTER;			// 此处调用无惨构造器
}
