package com.linkknown.enum0;

import org.junit.jupiter.api.Test;

/**
 * ö�ٲ�����
 * @author Administrator
 *
 */
public class EnumTest {
	
/************************************ ��ʹ��ö�ٵĳ��� **********************************************/	
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
		case Season.SEASON_SPRING:
			System.out.println("��������~");
			break;
		case Season.SEASON_SUMMER:
			System.out.println("��������~");
			break;
		case Season.SEASON_AUTUMM:
			System.out.println("��������~");
			break;
		case Season.SEASON_WINTER:
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
		checkSeason2(Season.SEASON_SPRING);
		// ��Ҫ�ṩ����ķ������б���
		String[] seasons = Season.getSeasons();
		for (String season : seasons) {
			System.out.println(season);
		}
	}
	

/************************************ ʹ��ö�ٵĳ��� **********************************************/	

	/**
	 * ö�ٲ���
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
	 * ö�ٱ���,ö���Դ� values �����������е�ö��ֵ
	 */
	@Test
	public void testEnumForEach () {
//		SeasonEnum[] values = SeasonEnum.values();
		for (SeasonEnum seasonEnum : SeasonEnum.values()) {
			System.out.println(seasonEnum);
		}
	}
	
	/**
	 * ö��ʹ��
	 */
	@Test
	public void testEnumUse () {
		SeasonEnum season = SeasonEnum.AUTUMN;
		switch (season) {
		case SPRING:
			System.out.println("��������~");
			break;
		case SUMMER:
			System.out.println("��������~");
			break;
		case AUTUMN:
			System.out.println("��������~");
			break;
		case WINTER:
			System.out.println("��������~");
			break;
		default:
			break;
		}
	}
	
	/**
	 * ö��ʹ��
	 * ����ö�ٹ�����
	 */
	@Test
	public void testEnumUse2 () {
		System.out.println(LoginStateEnum.LOGIN_SUCCESS);
		System.out.println(LoginStateEnum.LOGIN_FAILED);
		LoginStateEnum.LOGIN_SUCCESS.showDesc();
	}
	
	/**
	 * ö��ʹ��
	 * ����ö�����Ժ�ö�ٹ�����
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
	 * ö��ʹ�ã�������ٵĶ�����Ϣ��װ��ö���࣬��ȫ���޷����۸ģ�,����������ʱ����������������
	 */
	@Test
	public void testEnumUse4 () {
		for (SeasonEnum2 seasonEnum : SeasonEnum2.values()) {
			System.out.println(seasonEnum.getSeasonName() + seasonEnum.getSeasonDesc());
		}
	}
}
