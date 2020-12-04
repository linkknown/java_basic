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
	 * 校验邮箱是否合法
	 * 
	 * 字符串类的 matches 方法可以用来校验一个字符串是否满足一个正则
	 */
	@Test
	public void testRegex1 () {
		// . 正则转移 \. Java字符串再转义以下 \\.
		String regex = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com";
		System.out.println(regex);
		
		String str = "linkknown@163.com";
		String str2 = "389093982@qq.com";
		String str3 = "389093982";
		
		System.out.println(String.format("%s 是否是一个有效的邮箱：%b", str, str.matches(regex)));
		System.out.println(String.format("%s 是否是一个有效的邮箱：%b", str2, str2.matches(regex)));
		System.out.println(String.format("%s 是否是一个有效的邮箱：%b", str3, str3.matches(regex)));
	}
	
	/**
	 * matches 方法默认就是全匹配，底层相当于自动添加 ^ $
	 * ^ 表示以什么开头
	 * $ 表示以什么结尾
	 * ^ 和 $ 成为全匹配
	 */
	@Test
	public void testRegex2 () {
		String str = "linkknown@163.com";
		String str2 = "389093982@qq.com";
		String str3 = "389093982";
		
		String regex = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com$";
		System.out.println(String.format("%s 是否是一个有效的邮箱：%b", str, str.matches(regex)));
		System.out.println(String.format("%s 是否是一个有效的邮箱：%b", str2, str2.matches(regex)));
		System.out.println(String.format("%s 是否是一个有效的邮箱：%b", str3, str3.matches(regex)));
	}

	/**
	 * QQ 号规定 5-10 位数字，且不能以 0 开头
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
		// 数字至少出现一次
		System.out.println("11213234".matches("\\d+"));
		System.out.println("11213234".matches("[0-9]+"));
		System.out.println("11213234".matches("^[0-9]+$"));
		
		// 表示正数或者负数：10 和 -10
		System.out.println("10".matches("^-?[1-9][0-9]*$"));
		System.out.println("-10".matches("^-?[1-9][0-9]*$"));
		
		// 表示正数或者负数：10.01 和 -10.01
		System.out.println("10.01".matches("^-?[1-9][0-9]*(\\.[0-9]{1,2})?$"));
		System.out.println("-10.01".matches("^-?[1-9][0-9]*(\\.[0-9]{1,2})?$"));
		System.out.println("-10..01".matches("^-?[1-9][0-9]*(\\.[0-9]{1,2})?$"));
		System.out.println("-10.012".matches("^-?[1-9][0-9]*(\\.[0-9]{1,2})?$"));
	
		// 日期 2020-10-25
		System.out.println("2020-10-25".matches("^\\d{4}-\\d{2}-\\d{2}$"));				// 不够严谨
		System.out.println("2020-99-99".matches("^\\d{4}-\\d{2}-\\d{2}$"));				
		System.out.println("2020-99-99".matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$"));		// 够严谨		
		System.out.println("2020-10-25".matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$"));			
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
		System.out.println(Arrays.asList(str.split(regex)));
		
		System.out.println(Arrays.toString("1@2#3456@7#7#89".split("[@#]")));
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
		System.out.println(Arrays.toString(str.split(regex)));
		
		// 根据大写字母来分割
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
	 * 提取字符串中所有的数字
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
	 * 测试从 .java 文件中获取所有的方法名
	 * 满足 public void testReplaceAll2 () {   格式
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
