package com.linkknown.enum0;

import org.junit.jupiter.api.Test;

/**
 * 枚举测试类
 * @author Administrator
 *
 */
public class EnumTest {
	
/************************************ 不使用枚举的场景 **********************************************/	
	public static void checkSeason (String season) {
		if (season == null) {
			season = "";
		}
		season = season.trim().toLowerCase();
		switch (season) {
		case "spring":
			System.out.println("春天来啦~");
			break;
		case "summer":
			System.out.println("夏天来啦~");
			break;
		case "autumn":
			System.out.println("秋天来啦~");
			break;
		case "winter":
			System.out.println("冬天来啦~");
			break;
		default:
			System.out.println("您输入的参数有误！");
			break;
		}
	}
	
	/**
	 * 不使用枚举测试
	 */
	@Test
	public void testNoEnum () {
		checkSeason("SPRING");
		// 使用字符串或者数字容易导致拼写错误或者使用错误
		// 不利于遍历
		checkSeason("SPRRNG");
	}
	
	public static void checkSeason2 (String season) {
		switch (season) {
		case Season.SEASON_SPRING:
			System.out.println("春天来啦~");
			break;
		case Season.SEASON_SUMMER:
			System.out.println("夏天来啦~");
			break;
		case Season.SEASON_AUTUMM:
			System.out.println("秋天来啦~");
			break;
		case Season.SEASON_WINTER:
			System.out.println("冬天来啦~");
			break;
		default:
			System.out.println("您输入的参数有误！");
			break;
		}
	}

	/**
	 * 不使用枚举测试
	 */
	@Test
	public void testNoEnum2 () {
		checkSeason2(Season.SEASON_SPRING);
		// 需要提供额外的方法进行遍历
		String[] seasons = Season.getSeasons();
		for (String season : seasons) {
			System.out.println(season);
		}
	}
	

/************************************ 使用枚举的场景 **********************************************/	

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
	 * 枚举遍历,枚举自带 values 方法返回所有的枚举值
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
	 * 测试枚举构造器
	 */
	@Test
	public void testEnumUse2 () {
		System.out.println(LoginStateEnum.LOGIN_SUCCESS);
		System.out.println(LoginStateEnum.LOGIN_FAILED);
		LoginStateEnum.LOGIN_SUCCESS.showDesc();
	}
	
	/**
	 * 枚举使用
	 * 测试枚举属性和枚举构造器
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
