package com.linkknown.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class DateTest {

	/**
	 * 创建日期方式一
	 */
	@Test
	public void testDate1() {
		// 使用当前日期和时间来初始化对象
		Date date = new Date();
		System.out.println(date);
	}

	/**
	 * 创建日期方式二
	 */
	@Test
	public void testDate2() {
		// 构造函数接收一个参数，该参数是从1970年1月1日起的毫秒数
		Date date = new Date(30 * 365 * 24 * 3600 * 1000l);
		System.out.println(date);
	}

	/**
	 * 日期比较大小
	 */
	@Test
	public void testDate3() {
		Date date1 = new Date();
		Date date2 = new Date(30 * 365 * 24 * 3600 * 1000l);
		// 若当调用此方法的Date对象在指定日期之后返回true,否则返回false。
		System.out.println(date1.before(date2));
		// 若当调用此方法的Date对象在指定日期之前返回true,否则返回false。
		System.out.println(date1.after(date2));
	}

	/**
	 * 获取毫秒数
	 */

	public void testGetTime() {
		Date date = new Date();
		// 返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
		System.out.println(date.getTime());
	}

	/**
	 * 日期格式化
	 */
	@Test
	public void testDateFormate() {
		Date date = new Date();

		// 转换的格式，其中 yyyy 是完整的公元年，MM 是月份，dd 是日期，HH:mm:ss 是时、分、秒。
		// 注意:有的格式大写，有的格式小写，例如 MM 是月份，mm 是分；HH 是 24 小时制，而 hh 是 12 小时制。
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = dateFormat.format(date);
		System.out.println(dateStr);

		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateStr = dateFormat.format(date);
		System.out.println(dateStr);

		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateStr = dateFormat.format(date);
		System.out.println(dateStr);
		
		dateFormat = new SimpleDateFormat("HH:mm:ss");
		dateStr = dateFormat.format(date);
		System.out.println(dateStr);
		
		dateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
		dateStr = dateFormat.format(date);
		System.out.println(dateStr);
	}
	
	/**
	 * 日期格式化
	 * @throws ParseException 
	 */
	@Test
	public void testDateFormate2() throws ParseException {
		String dateStr = "2020-08-15 03:38:24";

		// 转换的格式，其中 yyyy 是完整的公元年，MM 是月份，dd 是日期，HH:mm:ss 是时、分、秒。
		// 注意:有的格式大写，有的格式小写，例如 MM 是月份，mm 是分；HH 是 24 小时制，而 hh 是 12 小时制。
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		System.out.println(dateFormat.parse(dateStr));
	}
}
