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
	}

	/**
	 * �������ڷ�ʽ��
	 */
	@Test
	public void testDate2() {
		// ���캯������һ���������ò����Ǵ�1970��1��1����ĺ�����
		Date date = new Date(30 * 365 * 24 * 3600 * 1000l);
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
	}

	/**
	 * ��ȡ������
	 */

	public void testGetTime() {
		Date date = new Date();
		// ������ 1970 �� 1 �� 1 �� 00:00:00 GMT ������ Date �����ʾ�ĺ�������
		System.out.println(date.getTime());
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
	}
}
