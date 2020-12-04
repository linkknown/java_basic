package com.linkknown.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class DateTest {

	/**
	 * �������ڷ�ʽһ
	 */
	@Test
	public void testDate1() {
		// ʹ�õ�ǰ���ں�ʱ������ʼ������
		Date date = new Date();
		System.out.println(date);
		
		// ������ 1970 �� 1 �� 1 �� 00:00:00 GMT ������ Date �����ʾ�ĺ�������
		System.out.println(date.getTime());
	}

	/**
	 * �������ڷ�ʽ��
	 */
	@Test
	public void testDate2() {
		// ���캯������һ���������ò����Ǵ�1970��1��1����ĺ�����
		Date date = new Date(0 + 30 * 365 * 24 * 3600 * 1000l); 	// ʱ��Ԫ�� 30 ��֮��
		System.out.println(date);
		
		date = new Date(new Date().getTime() + 24 * 3600 * 1000l);	// ��������ʱ���
		System.out.println(date);
	}

	/**
	 * ���ڱȽϴ�С
	 */
	@Test
	public void testDate3() {
		Date date1 = new Date();
		Date date2 = new Date(30 * 365 * 24 * 3600 * 1000l);
		// �������ô˷�����Date������ָ������֮�󷵻�true,���򷵻�false��
		System.out.println(date1.before(date2));
		// �������ô˷�����Date������ָ������֮ǰ����true,���򷵻�false��
		System.out.println(date1.after(date2));
		
		System.out.println(between(date2, new Date(0), new Date()));
	}
	
	public static boolean between (Date date, Date start, Date end) {
		return date.after(start) && date.before(end);
	}

	/**
	 * ���ڸ�ʽ��
	 */
	@Test
	public void testDateFormate() {
		Date date = new Date();

		// ת���ĸ�ʽ������ yyyy �������Ĺ�Ԫ�꣬MM ���·ݣ�dd �����ڣ�HH:mm:ss ��ʱ���֡��롣
		// ע��:�еĸ�ʽ��д���еĸ�ʽСд������ MM ���·ݣ�mm �Ƿ֣�HH �� 24 Сʱ�ƣ��� hh �� 12 Сʱ�ơ�
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = dateFormat.format(date);
//		String dateStr = dateFormat.format(new Date(date.getTime() + 12 * 3600 * 1000));	
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
		
		dateFormat = new SimpleDateFormat("yyyy��MM��dd�� hhʱmm��ss��");
		dateStr = dateFormat.format(date);
		System.out.println(dateStr);
	}
	
	/**
	 * ���ڸ�ʽ��
	 * @throws ParseException 
	 */
	@Test
	public void testDateFormate2() throws ParseException {
		String dateStr = "2020-08-15 03:38:24";

		// ת���ĸ�ʽ������ yyyy �������Ĺ�Ԫ�꣬MM ���·ݣ�dd �����ڣ�HH:mm:ss ��ʱ���֡��롣
		// ע��:�еĸ�ʽ��д���еĸ�ʽСд������ MM ���·ݣ�mm �Ƿ֣�HH �� 24 Сʱ�ƣ��� hh �� 12 Сʱ�ơ�
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		System.out.println(dateFormat.parse(dateStr));
		
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��");
		
		System.out.println(dateFormat2.format(dateFormat.parse(dateStr)));
	}
}
