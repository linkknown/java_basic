package com.linkknown.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class CalenderTest {

	/**
	 * ���� Calendar
	 * �˽� Calendar ��������������֪ʶ
	 */
	@Test
	public void testCalender () {
		// �ײ㴴���ľ��� java.util.GregorianCalendar ���������������ͨ�õ�������
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		System.out.println(date);
		
		calendar.setTime(new Date(30 * 365 * 24 * 3600 * 1000l));
		System.out.println(calendar);
	}
	
	/**
	 * �޸� Calendar
	 */
	@Test
	public void testCalender2 () {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
		
		Calendar calendar = Calendar.getInstance();
		// ����������
		calendar.set(Calendar.YEAR, 2020);
		calendar.set(Calendar.MONTH, 1);					// �·�Ҫ��1����Ϊ�·��Ǵ�0��ʼ��
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		// ��ʽ�����
		System.out.println(simpleDateFormat.format(calendar.getTime()));		// 2020-02-01 15:57:57
		// ��ȡ���
		System.out.println(calendar.get(Calendar.YEAR));
		// ��ȡ��ǰϵͳʱ���е��µ����һ��(��ȡ����ʱ���������������ֵ)
		System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));	
		System.out.println(calendar.getActualMaximum(Calendar.MONTH));			// ���� 11 ��ʾ 12 ��

		// ����һ��
		calendar.add(Calendar.YEAR, 1);
		System.out.println(simpleDateFormat.format(calendar.getTime()));		// 2021-02-01 15:57:57
	}
}
