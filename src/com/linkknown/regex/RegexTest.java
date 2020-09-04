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
 * Java 正则测试
 * @author Administrator
 *
 */
public class RegexTest {

	/**
	 * 校验邮箱
	 */
	@Test
	public void testRegex () {
		// . 正则转移 \. Java字符串再转义以下 \\.
		String regex = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com";
		System.out.println(regex);
		
		String str = "linkknown@163.com";
//		String str = "www.linkknown.com";
		// String 的 matches 方法,无论是否加 ^$,都是做全匹配
		System.out.println("是否是合法的邮箱 " + str.matches(regex));
	}
	
	/**
	 * 校验邮箱
	 */
	@Test
	public void testRegex2 () {
		String regex = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com$";
		
		String str = "linkknown@163.com";
//		String str = "www.linkknown.com";
		System.out.println("是否是合法的邮箱 " + str.matches(regex));
	}

	/**
	 * 验证 QQ
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
	 * 验证 18 位身份证
	 */
	@Test
	public void testIDNumber () {
	    // 定义判别用户身份证号的正则表达式（18位，最后一位可以为字母）
	    String regex = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
	            "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
	    //假设18位身份证号码:41000119910101123X  410001 19910101 123X
	    //^开头
	    //[1-9] 第一位1-9中的一个      4
	    //\\d{5} 五位数字           10001（前六位省市县地区）
	    //(18|19|20)                19（现阶段可能取值范围18xx-20xx年）
	    //\\d{2}                    91（年份）
	    //((0[1-9])|(10|11|12))     01（月份）
	    //(([0-2][1-9])|10|20|30|31)01（日期）
	    //\\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
	    //[0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
	    //$结尾

        System.out.println("41000119910101123X".matches(regex));
        System.out.println("141000119910101123X".matches(regex));
	}
	
	
	/**
	 * 根据正则分割
	 */
	@Test
	public void testSplit () {
		String regex = ",";
		String str = "a,b,c,d,e,f";
		System.out.println(str.split(regex));
		System.out.println(Arrays.asList(str.split(regex)));
	}
	
	/**
	 * 根据正则分割
	 * 根据大写字母分割
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
		System.out.println("图片1" + "." + arr[1]);
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
	 * 敏感字符过滤
	 */
	@Test
	public void testReplaceAll () {
		String regex = "(笨蛋|蠢|屎)";
		String str = "笨蛋啊，你太蠢了，去屎吧";
		String replaceStr = str.replaceAll(regex, "???");
		System.out.println(replaceStr);
	}

	@Test
	public void testReplaceAll2 () {
		// 去除所有数字
		System.out.println("afdhisfshf234234dsfsdf433453454dfgdgfdg554456".replaceAll("\\d", ""));
		// 去除所有英文字母
		System.out.println("afdhisfshf234234dsfsdf433453454dfgdgfdg554456".replaceAll("[a-zA-Z]", ""));
	}
	
	/**
	 * 测试从 .java 文件中获取所有的方法名
	 * 满足 public void testReplaceAll2 () {   格式
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
	 * 提取字符串中所有的数字
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
