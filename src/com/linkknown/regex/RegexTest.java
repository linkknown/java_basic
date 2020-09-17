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
	
	@Test
	public void testRegex () {
//		һ��У�����ֵı��ʽ
//		1 ���֣�^[0-9]*$
//		2 nλ�����֣�^\d{n}$
//		3 ����nλ�����֣�^\d{n,}$
//		4 m-nλ�����֣�^\d{m,n}$
//		5 ��ͷ��㿪ͷ�����֣�^(0|[1-9][0-9]*)$
//		6 ���㿪ͷ��������λС�������֣�^([1-9][0-9]*)+(.[0-9]{1,2})?$
//		7 ��1-2λС��������������^(\-)?\d+(\.\d{1,2})?$
//		8 ��������������С����^(\-|\+)?\d+(\.\d+)?$
//		9 ����λС������ʵ����^[0-9]+(.[0-9]{2})?$
//		10 ��1~3λС������ʵ����^[0-9]+(.[0-9]{1,3})?$
//		11 �������������^[1-9]\d*$ �� ^([1-9][0-9]*){1,3}$ �� ^\+?[1-9][0-9]*$
//		12 ����ĸ�������^\-[1-9][]0-9"*$ �� ^-[1-9]\d*$
//		13 �Ǹ�������^\d+$ �� ^[1-9]\d*|0$
//		14 ����������^-[1-9]\d*|0$ �� ^((-\d+)|(0+))$
//		15 �Ǹ���������^\d+(\.\d+)?$ �� ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$
//		16 ������������^((-\d+(\.\d+)?)|(0+(\.0+)?))$ �� ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$
//		17 ����������^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$ �� ^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$
//		18 ����������^-([1-9]\d*\.\d*|0\.\d*[1-9]\d*)$ �� ^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$
//		19 ��������^(-?\d+)(\.\d+)?$ �� ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$
		
//		����У���ַ��ı��ʽ
//		1 ���֣�^[\u4e00-\u9fa5]{0,}$
//		2 Ӣ�ĺ����֣�^[A-Za-z0-9]+$ �� ^[A-Za-z0-9]{4,40}$
//		3 ����Ϊ3-20�������ַ���^.{3,20}$
//		4 ��26��Ӣ����ĸ��ɵ��ַ�����^[A-Za-z]+$
//		5 ��26����дӢ����ĸ��ɵ��ַ�����^[A-Z]+$
//		6 ��26��СдӢ����ĸ��ɵ��ַ�����^[a-z]+$
//		7 �����ֺ�26��Ӣ����ĸ��ɵ��ַ�����^[A-Za-z0-9]+$
//		8 �����֡�26��Ӣ����ĸ�����»�����ɵ��ַ�����^\w+$ �� ^\w{3,20}$
//		9 ���ġ�Ӣ�ġ����ְ����»��ߣ�^[\u4E00-\u9FA5A-Za-z0-9_]+$
//		10 ���ġ�Ӣ�ġ����ֵ��������»��ߵȷ��ţ�^[\u4E00-\u9FA5A-Za-z0-9]+$ �� ^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$
//		11 �������뺬��^%&',;=?$\"���ַ���[^%&',;=?$\x22]+
//		12 ��ֹ���뺬��~���ַ���[^~\x22]+
		
		
//		��������������ʽ
//		1 Email��ַ��^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$
//		2 ������[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?
//		3 InternetURL��[a-zA-z]+://[^\s]* �� ^https://([\w-]+\.)+[\w-]+(/[\w-./?%&=]*)?$
//		4 �ֻ����룺^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$
//		5 �绰����("XXX-XXXXXXX"��"XXXX-XXXXXXXX"��"XXX-XXXXXXX"��"XXX-XXXXXXXX"��"XXXXXXX"��"XXXXXXXX)��^(\(\d{3,4}-)|\d{3.4}-)?\d{7,8}$ 
//		6 ���ڵ绰����(0511-4405222��021-87888822)��\d{3}-\d{8}|\d{4}-\d{7}
//		7 ���֤�ţ�
//				15��18λ���֤��^\d{15}|\d{18}$
//				15λ���֤��^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$
//				18λ���֤��^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$
//		8 �����֤����(���֡���ĸx��β)��^([0-9]){7,18}(x|X)?$ �� ^\d{8,18}|[0-9x]{8,18}|[0-9X]{8,18}?$
//		9 �ʺ��Ƿ�Ϸ�(��ĸ��ͷ������5-16�ֽڣ�������ĸ�����»���)��^[a-zA-Z][a-zA-Z0-9_]{4,15}$
//		10 ����(����ĸ��ͷ��������6~18֮�䣬ֻ�ܰ�����ĸ�����ֺ��»���)��^[a-zA-Z]\w{5,17}$
//		11 ǿ����(���������Сд��ĸ�����ֵ���ϣ�����ʹ�������ַ���������8-10֮��)��^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$ 
//		12 ���ڸ�ʽ��^\d{4}-\d{1,2}-\d{1,2}
//		13 һ���12����(01��09��1��12)��^(0?[1-9]|1[0-2])$
//		14 һ���µ�31��(01��09��1��31)��^((0?[1-9])|((1|2)[0-9])|30|31)$ 
//		15 Ǯ�������ʽ��
//		16 1.������Ǯ�ı�ʾ��ʽ���ǿ��Խ���:"10000.00" �� "10,000.00", ��û�� "��" �� "10000" �� "10,000"��^[1-9][0-9]*$ 
//		17 2.���ʾ����һ������0��ͷ������,����,��Ҳ��ζ��һ���ַ�"0"��ͨ��,�������ǲ����������ʽ��^(0|[1-9][0-9]*)$ 
//		18 3.һ��0����һ������0��ͷ������.���ǻ���������ͷ��һ�����ţ�^(0|-?[1-9][0-9]*)$ 
//		19 4.���ʾһ��0����һ������Ϊ���Ŀ�ͷ��Ϊ0������.���û���0��ͷ����.�Ѹ��ŵ�Ҳȥ��,��ΪǮ�ܲ����Ǹ��İ�.��������Ҫ�ӵ���˵�����ܵ�С�����֣�^[0-9]+(.[0-9]+)?$ 
//		20 5.����˵������,С�����������Ӧ����1λ��,����"10."�ǲ�ͨ����,���� "10" �� "10.2" ��ͨ���ģ�^[0-9]+(.[0-9]{2})?$ 
//		21 6.�������ǹ涨С��������������λ,�������Ϊ̫������,����������^[0-9]+(.[0-9]{1,2})?$ 
//		22 7.�����������û�ֻдһλС��.�������Ǹÿ��������еĶ�����,���ǿ���������^[0-9]{1,3}(,[0-9]{3})*(.[0-9]{1,2})?$ 
//		23 8.1��3������,������������ ����+3������,���ų�Ϊ��ѡ,�����Ǳ��룺^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$ 
//		24 ��ע����������ս����,������"+"������"*"����������ÿ��ַ���Ҳ���Խ��ܵĻ�(���,Ϊʲô?)���,���������ú���ʱȥ��ȥ���Ǹ���б��,һ��Ĵ���������
//		25 xml�ļ���^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$
//		26 �����ַ���������ʽ��[\u4e00-\u9fa5]
//		27 ˫�ֽ��ַ���[^\x00-\xff] (�����������ڣ��������������ַ����ĳ���(һ��˫�ֽ��ַ����ȼ�2��ASCII�ַ���1))
//		28 �հ��е�������ʽ��\n\s*\r (��������ɾ���հ���)
//		29 HTML��ǵ�������ʽ��<(\S*?)[^>]*>.*?|<.*? /> (���������İ汾̫��⣬�������Ҳ�����ܲ��֣����ڸ��ӵ�Ƕ�ױ����������Ϊ��)
//		30 ��β�հ��ַ���������ʽ��^\s*|\s*$��(^\s*)|(\s*$) (��������ɾ��������β�Ŀհ��ַ�(�����ո��Ʊ������ҳ���ȵ�)���ǳ����õı��ʽ)
//		31 ��ѶQQ�ţ�[1-9][0-9]{4,} (��ѶQQ�Ŵ�10000��ʼ)
//		32 �й��������룺[1-9]\d{5}(?!\d) (�й���������Ϊ6λ����)
//		33 IP��ַ��\d+\.\d+\.\d+\.\d+ (��ȡIP��ַʱ����)
	}

	/**
	 * У������
	 */
	@Test
	public void testRegex1 () {
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
