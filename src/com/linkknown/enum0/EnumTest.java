package com.linkknown.enum0;

import org.junit.jupiter.api.Test;

/**
 * 枚举测试类
 * @author Administrator
 *
 */
public class EnumTest {

	/**
	 * 枚举测试
	 */
	@Test
	public void testEnum () {
		System.out.println(SeasonEnum.SPRING);
		System.out.println(SeasonEnum.SUMMER);
		System.out.println(SeasonEnum.AUTUMN);
		System.out.println(SeasonEnum.WINTER);

		System.out.println(SeasonEnum.WINTER.getClass().getName());
	}
	
	/**
	 * 枚举遍历
	 */
	@Test
	public void testEnumForEach () {
//		SeasonEnum[] values = SeasonEnum.values();
		for (SeasonEnum seasonEnum : SeasonEnum.values()) {
			System.out.println(seasonEnum);
		}
	}
	
	/**
	 * 枚举使用
	 */
	@Test
	public void testEnumUse () {
		SeasonEnum season = SeasonEnum.AUTUMN;
		switch (season) {
		case SPRING:
			System.out.println("春天来啦~");
			break;
		case SUMMER:
			System.out.println("夏天来啦~");
			break;
		case AUTUMN:
			System.out.println("秋天来啦~");
			break;
		case WINTER:
			System.out.println("冬天来啦~");
			break;
		default:
			break;
		}
	}
	
	/**
	 * 枚举使用
	 */
	@Test
	public void testEnumUse2 () {
		System.out.println(LoginStateEnum.LOGIN_SUCCESS);
		System.out.println(LoginStateEnum.LOGIN_FAILED);
		LoginStateEnum.LOGIN_SUCCESS.showDesc();
	}
	
	/**
	 * 枚举使用
	 */
	@Test
	public void testEnumUse3 () {
		for (AddressEnum addressEnum : AddressEnum.values()) {
			System.out.println(addressEnum.getProvince());
			System.out.println(addressEnum.getCity());
			System.out.println(addressEnum.getCountry());
		}
	}
	
	/**
	 * 枚举使用，将可穷举的对象信息封装成枚举类，安全（无法被篡改）,将程序运行时错误提升到编译器
	 */
	@Test
	public void testEnumUse4 () {
		for (SeasonEnum2 seasonEnum : SeasonEnum2.values()) {
			System.out.println(seasonEnum.getSeasonName() + seasonEnum.getSeasonDesc());
		}
	}
}
