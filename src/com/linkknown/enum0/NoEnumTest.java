package com.linkknown.enum0;

import org.junit.jupiter.api.Test;

/**
 * 不使用枚举的场景
 * @author Administrator
 *
 */
public class NoEnumTest {

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
		case NoEnumSeason.SEASON_SPRING:
			System.out.println("春天来啦~");
			break;
		case NoEnumSeason.SEASON_SUMMER:
			System.out.println("夏天来啦~");
			break;
		case NoEnumSeason.SEASON_AUTUMM:
			System.out.println("秋天来啦~");
			break;
		case NoEnumSeason.SEASON_WINTER:
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
		checkSeason2(NoEnumSeason.SEASON_SPRING);
		// 需要提供额外的方法进行遍历
		String[] seasons = NoEnumSeason.getSeasons();
		for (String season : seasons) {
			System.out.println(season);
		}
	}
}
