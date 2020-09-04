package com.linkknown.enum0;

import org.junit.jupiter.api.Test;

/**
 * ö�ٲ�����
 * @author Administrator
 *
 */
public class EnumTest {

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
	 * ö�ٱ���
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
	 */
	@Test
	public void testEnumUse2 () {
		System.out.println(LoginStateEnum.LOGIN_SUCCESS);
		System.out.println(LoginStateEnum.LOGIN_FAILED);
		LoginStateEnum.LOGIN_SUCCESS.showDesc();
	}
	
	/**
	 * ö��ʹ��
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
