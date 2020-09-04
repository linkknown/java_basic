package com.linkknown.enum0;

import org.junit.jupiter.api.Test;

/**
 * ��ʹ��ö�ٵĳ���
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
			System.out.println("��������~");
			break;
		case "summer":
			System.out.println("��������~");
			break;
		case "autumn":
			System.out.println("��������~");
			break;
		case "winter":
			System.out.println("��������~");
			break;
		default:
			System.out.println("������Ĳ�������");
			break;
		}
	}
	
	/**
	 * ��ʹ��ö�ٲ���
	 */
	@Test
	public void testNoEnum () {
		checkSeason("SPRING");
		// ʹ���ַ��������������׵���ƴд�������ʹ�ô���
		// �����ڱ���
		checkSeason("SPRRNG");
	}
	
	public static void checkSeason2 (String season) {
		switch (season) {
		case NoEnumSeason.SEASON_SPRING:
			System.out.println("��������~");
			break;
		case NoEnumSeason.SEASON_SUMMER:
			System.out.println("��������~");
			break;
		case NoEnumSeason.SEASON_AUTUMM:
			System.out.println("��������~");
			break;
		case NoEnumSeason.SEASON_WINTER:
			System.out.println("��������~");
			break;
		default:
			System.out.println("������Ĳ�������");
			break;
		}
	}

	/**
	 * ��ʹ��ö�ٲ���
	 */
	@Test
	public void testNoEnum2 () {
		checkSeason2(NoEnumSeason.SEASON_SPRING);
		// ��Ҫ�ṩ����ķ������б���
		String[] seasons = NoEnumSeason.getSeasons();
		for (String season : seasons) {
			System.out.println(season);
		}
	}
}
