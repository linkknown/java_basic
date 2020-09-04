package com.linkknown.regex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * Java �������
 * @author Administrator
 *
 */
public class RegexTest {

	/**
	 * У������
	 */
	@Test
	public void testRegex () {
		// . ����ת�� \. Java�ַ�����ת������ \\.
		String regex = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com";
		System.out.println(regex);
		
		String str = "linkknown@163.com";
//		String str = "www.linkknown.com";
		// String �� matches ����,�����Ƿ�� ^$,������ȫƥ��
		System.out.println("�Ƿ��ǺϷ������� " + str.matches(regex));
	}
	
	/**
	 * У������
	 */
	@Test
	public void testRegex2 () {
		String regex = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com$";
		
		String str = "linkknown@163.com";
//		String str = "www.linkknown.com";
		System.out.println("�Ƿ��ǺϷ������� " + str.matches(regex));
	}

	/**
	 * ��֤ QQ
	 */
	@Test
	public void testQQ () {
		String regex = "^[1-9][0-9]{4,9}$";
	
		System.out.println("389093982".matches(regex));
		System.out.println("11111111".matches(regex));
		System.out.println("01111111".matches(regex));
		System.out.println("a1111111".matches(regex));
	}
	
	/**
	 * ��֤ 18 λ���֤
	 */
	@Test
	public void testIDNumber () {
	    // �����б��û����֤�ŵ�������ʽ��18λ�����һλ����Ϊ��ĸ��
	    String regex = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
	            "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
	    //����18λ���֤����:41000119910101123X  410001 19910101 123X
	    //^��ͷ
	    //[1-9] ��һλ1-9�е�һ��      4
	    //\\d{5} ��λ����           10001��ǰ��λʡ���ص�����
	    //(18|19|20)                19���ֽ׶ο���ȡֵ��Χ18xx-20xx�꣩
	    //\\d{2}                    91����ݣ�
	    //((0[1-9])|(10|11|12))     01���·ݣ�
	    //(([0-2][1-9])|10|20|30|31)01�����ڣ�
	    //\\d{3} ��λ����            123����ʮ��λ���������У�ż������Ů��
	    //[0-9Xx] 0123456789Xx���е�һ�� X����ʮ��λΪУ��ֵ��
	    //$��β

        System.out.println("41000119910101123X".matches(regex));
        System.out.println("141000119910101123X".matches(regex));
	}
	
	
	/**
	 * ��������ָ�
	 */
	@Test
	public void testSplit () {
		String regex = ",";
		String str = "a,b,c,d,e,f";
		System.out.println(str.split(regex));
		System.out.println(Arrays.asList(str.split(regex)));
	}
	
	/**
	 * ��������ָ�
	 * ���ݴ�д��ĸ�ָ�
	 */
	@Test
	public void testSplit2 () {
		String regex = "[A-Z]";
		String str = "aAbBcCdDeEfF";
		System.out.println(str.split(regex));
		System.out.println(Arrays.asList(str.split(regex)));
	}
	
	@Test
	public void testSplit3 () {
		String regex = "\\.";
		String str = "1.jpg";
		String[] arr = str.split(regex);
		System.out.println("ͼƬ1" + "." + arr[1]);
	}
	
	@Test
	public void testSplit4 () {
		String regex = "[\\.,]";
		String str = "1.2.3.4.5.6...7.8.9,,,,,,.0";
		String[] arr = str.split(regex);
		System.out.println(arr.length);
		System.out.println(Arrays.asList(arr));
	}
	
	/**
	 * �����ַ�����
	 */
	@Test
	public void testReplaceAll () {
		String regex = "(����|��|ʺ)";
		String str = "����������̫���ˣ�ȥʺ��";
		String replaceStr = str.replaceAll(regex, "???");
		System.out.println(replaceStr);
	}

	@Test
	public void testReplaceAll2 () {
		// ȥ����������
		System.out.println("afdhisfshf234234dsfsdf433453454dfgdgfdg554456".replaceAll("\\d", ""));
		// ȥ������Ӣ����ĸ
		System.out.println("afdhisfshf234234dsfsdf433453454dfgdgfdg554456".replaceAll("[a-zA-Z]", ""));
	}
	
	/**
	 * ���Դ� .java �ļ��л�ȡ���еķ�����
	 * ���� public void testReplaceAll2 () {   ��ʽ
	 * @throws IOException 
	 */
	@Test
	public void testPattern () throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\teacher\\code\\eclipse_workspace\\linkknown\\src\\com\\linkknown\\regex\\RegexTest.java")));
		String str = "";
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			str += line;
		}
		bufferedReader.close();
		
		Pattern pattern = Pattern.compile("(\\s)+(public|private|protected)(\\s)+[a-zA-Z_$]{1}[a-zA-Z0-9_$]+(\\s)+[a-zA-Z_$]{1}[a-zA-Z0-9_$]+(\\s)?\\(");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			String methodName = matcher.group();
			System.out.println("find methodName : " + methodName);
		}
	}
	
	/**
	 * ��ȡ�ַ��������е�����
	 * @throws IOException
	 */
	@Test
	public void testPattern2 () throws IOException {
		String str = "sfhi3223dfssfiiisd3232kf3443kf33434534kj3232423grerwreew3232355";
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			String matchStr = matcher.group();
			System.out.println(matchStr);
		}
	}
}
