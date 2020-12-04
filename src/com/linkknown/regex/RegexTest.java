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
	 * У�������Ƿ�Ϸ�
	 * 
	 * �ַ������ matches ������������У��һ���ַ����Ƿ�����һ������
	 */
	@Test
	public void testRegex1 () {
		// . ����ת�� \. Java�ַ�����ת������ \\.
		String regex = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com";
		System.out.println(regex);
		
		String str = "linkknown@163.com";
		String str2 = "389093982@qq.com";
		String str3 = "389093982";
		
		System.out.println(String.format("%s �Ƿ���һ����Ч�����䣺%b", str, str.matches(regex)));
		System.out.println(String.format("%s �Ƿ���һ����Ч�����䣺%b", str2, str2.matches(regex)));
		System.out.println(String.format("%s �Ƿ���һ����Ч�����䣺%b", str3, str3.matches(regex)));
	}
	
	/**
	 * matches ����Ĭ�Ͼ���ȫƥ�䣬�ײ��൱���Զ���� ^ $
	 * ^ ��ʾ��ʲô��ͷ
	 * $ ��ʾ��ʲô��β
	 * ^ �� $ ��Ϊȫƥ��
	 */
	@Test
	public void testRegex2 () {
		String str = "linkknown@163.com";
		String str2 = "389093982@qq.com";
		String str3 = "389093982";
		
		String regex = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com$";
		System.out.println(String.format("%s �Ƿ���һ����Ч�����䣺%b", str, str.matches(regex)));
		System.out.println(String.format("%s �Ƿ���һ����Ч�����䣺%b", str2, str2.matches(regex)));
		System.out.println(String.format("%s �Ƿ���һ����Ч�����䣺%b", str3, str3.matches(regex)));
	}

	/**
	 * QQ �Ź涨 5-10 λ���֣��Ҳ����� 0 ��ͷ
	 */
	@Test
	public void testRegex3 () {
		String regex = "^[1-9][0-9]{4,9}$";
		System.out.println("389893982".matches(regex));
		System.out.println("11111".matches(regex));
		System.out.println("1111111111".matches(regex));
		System.out.println("111".matches(regex));
		System.out.println("1111111111111".matches(regex));
		System.out.println("000112323".matches(regex));
		System.out.println("a323232233".matches(regex));
	}
	
	@Test
	public void testRegex4 () {
		// �������ٳ���һ��
		System.out.println("11213234".matches("\\d+"));
		System.out.println("11213234".matches("[0-9]+"));
		System.out.println("11213234".matches("^[0-9]+$"));
		
		// ��ʾ�������߸�����10 �� -10
		System.out.println("10".matches("^-?[1-9][0-9]*$"));
		System.out.println("-10".matches("^-?[1-9][0-9]*$"));
		
		// ��ʾ�������߸�����10.01 �� -10.01
		System.out.println("10.01".matches("^-?[1-9][0-9]*(\\.[0-9]{1,2})?$"));
		System.out.println("-10.01".matches("^-?[1-9][0-9]*(\\.[0-9]{1,2})?$"));
		System.out.println("-10..01".matches("^-?[1-9][0-9]*(\\.[0-9]{1,2})?$"));
		System.out.println("-10.012".matches("^-?[1-9][0-9]*(\\.[0-9]{1,2})?$"));
	
		// ���� 2020-10-25
		System.out.println("2020-10-25".matches("^\\d{4}-\\d{2}-\\d{2}$"));				// �����Ͻ�
		System.out.println("2020-99-99".matches("^\\d{4}-\\d{2}-\\d{2}$"));				
		System.out.println("2020-99-99".matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$"));		// ���Ͻ�		
		System.out.println("2020-10-25".matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$"));			
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
		System.out.println(Arrays.asList(str.split(regex)));
		
		System.out.println(Arrays.toString("1@2#3456@7#7#89".split("[@#]")));
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
		System.out.println(Arrays.toString(str.split(regex)));
		
		// ���ݴ�д��ĸ���ָ�
		System.out.println(Arrays.toString("afAs4DFS34GFFFGdRfsfhDSif32F3232SfdgsF2323".split("[A-Z]")));
		System.out.println(Arrays.toString("afAs4DFS34GFFFGdRfsfhDSif32F3232SfdgsF2323".split("[A-Z]{2}")));
		System.out.println(Arrays.toString("afAs4DFS34GFFFGdRfsfhDSif32F3232SfdgsF2323".split("[A-Z]{3}")));
	}
	
	@Test
	public void testSplit3 () {
		String regex = "[\\.,]";
		String str = "1.2.3.4.5.6...7.8.9,,,,,,.0";
		String[] arr = str.split(regex);
		System.out.println(arr.length);
		System.out.println(Arrays.toString(arr));
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
	 * ��ȡ�ַ��������е�����
	 * @throws IOException
	 */
	@Test
	public void testPattern () throws IOException {
		String str = "sfhi3223dfssfiiisd3232kf3443kf33434534kj3232423grerwreew3232355";
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			String matchStr = matcher.group();
			System.out.println(matchStr);
		}
	}
	
	/**
	 * ���Դ� .java �ļ��л�ȡ���еķ�����
	 * ���� public void testReplaceAll2 () {   ��ʽ
	 * @throws IOException 
	 */
	@Test
	public void testPattern2 () throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
				new FileInputStream("E:\\teacher\\code\\eclipse_workspace\\linkknown\\src\\com\\linkknown\\regex\\RegexTest.java")));
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
	
}
