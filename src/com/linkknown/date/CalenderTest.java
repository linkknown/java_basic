package com.linkknown.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class CalenderTest {

	/**
	 * 创建 Calendar
	 * 了解 Calendar 类包含的面向对象知识
	 */
	@Test
	public void testCalender () {
		// 底层创建的就是 java.util.GregorianCalendar （格里高里历，即通用的阳历）
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		System.out.println(date);
		
		calendar.setTime(new Date(30 * 365 * 24 * 3600 * 1000l));
		System.out.println(calendar);
	}
	
	/**
	 * 修改 Calendar
	 */
	@Test
	public void testCalender2 () {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
		
		Calendar calendar = Calendar.getInstance();
		// 设置年月日
		calendar.set(Calendar.YEAR, 2020);
		calendar.set(Calendar.MONTH, 1);					// 月份要加1，因为月份是从0开始的
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		// 格式化输出
		System.out.println(simpleDateFormat.format(calendar.getTime()));		// 2020-02-01 15:57:57
		// 获取年份
		System.out.println(calendar.get(Calendar.YEAR));
		// 获取当前系统时间中当月的最后一天(获取给定时间分量所允许的最大值)
		System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));	
		System.out.println(calendar.getActualMaximum(Calendar.MONTH));			// 数字 11 表示 12 月

		// 增加一年
		calendar.add(Calendar.YEAR, 1);
		System.out.println(simpleDateFormat.format(calendar.getTime()));		// 2021-02-01 15:57:57
	}
}
