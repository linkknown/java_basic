package com.linkknown.exception;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * 异常处理
 * 
 * @author Administrator
 *
 */
public class ExceptionTest {
	/*************************************** 模拟非检查异常 ******************************************/ 
	/**
	 * 模拟非检查异常
	 * @param str
	 * @return
	 */
	private static String toUpper(String str) {
		return str.toUpperCase();
	}
	
	/**
	 * 测试空指针异常
	 */
	@Test
	public void testNullPointerException () {
		System.out.println(toUpper(null));
	}
	
	/**
	 * 测试数组下标越界异常
	 */
	@Test
	public void testArrayIndexOutOfBoundsException () {
		int[] intArr = new int[2];
		intArr[2] = 10;
	}
	
	/**
	 * 测试算数异常
	 */
	@Test
	public void testArithmeticException () {
		int i = 10 / 0;
	}
	
	
	/*************************************** 模拟检查异常 ******************************************/ 
	/**
	 * 测试检查异常
	 */
	@Test
	public void testUnknownHostException() {
		InetAddress ia = null;
		try {
			ia = InetAddress.getLocalHost();

			String localname = ia.getHostName();
			String localip = ia.getHostAddress();
			System.out.println("本机名称是：" + localname);
			System.out.println("本机的ip是 ：" + localip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试检查异常 IOException
	 */
	@Test
	public void testIOException() {
		FileWriter fw = null;
        try{
            //可能会产出异常的代码
            fw = new FileWriter("D:\\helloworld.txt",true);
            for (int i = 0; i <10 ; i++) {
                fw.write("HelloWorld"+i+"\r\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            //一定会指定的代码
            //创建对象失败了,fw的默认值就是null,null是不能调用方法的,会抛出NullPointerException,需要增加一个判断,不是null在把资源释放
            if(fw!=null){
                try {
                    //fw.close方法声明抛出了IOException异常对象,所以我们就的处理这个异常对象,要么throws,要么try catch
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
	}
	
	
	/*************************************** 检查处理 ******************************************/ 
	/**
	 * try catch 捕获并处理异常
	 */
	@Test
	public void testException () {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(dateFormat.format(date));
		
		try {
			System.out.println(dateFormat.parse("2020-09-10 15:25:57AAA"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * throws 抛出异常
	 * @throws ParseException 
	 */
	@Test
	public void testException2 () throws ParseException {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(dateFormat.format(date));
		
		System.out.println(dateFormat.parse("2020-09-10 15:25:57AAA"));
	}

	/**
	 * try catch 捕获并处理异常
	 */
	@Test
	public void testIOException2() {
		FileWriter fw = null;
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			fw = new FileWriter("D:/helloworld.txt");
			fw.write(ip);
		} catch (UnknownHostException e) {			// UnknownHostException extends IOException
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 抛出异常
	 * @throws UnknownHostException 
	 */
	private static String getLocalIp () throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
	
	/**
	 * 同时抛出多个异常
	 * @throws UnknownHostException
	 * @throws ParseException
	 */
	@Test
	public void testThrows () throws UnknownHostException, ParseException {
		System.out.println(getLocalIp());
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(dateFormat.format(date));
		
		System.out.println(dateFormat.parse("2020-09-10 15:25:57AAA"));
	}
	
	/**
	 * 捕获异常并重新抛出
	 * @throws UnknownHostException 
	 */
	private static String getLocalIp2 () throws UnknownHostException {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			throw e;
		}
	}
	
	/**
	 * 捕获异常并重新抛出
	 * @throws Exception 
	 */
	private static String getLocalIp3 () throws Exception {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * 抛出多个异常
	 * @throws UnknownHostException
	 * @throws Exception
	 */
	@Test
	public void testGetLocalIp () throws UnknownHostException, Exception {
		System.out.println(getLocalIp());
		System.out.println(getLocalIp2());
		System.out.println(getLocalIp3());
	}
	
	/**
	 * 抛出最大的异常
	 * @throws Exception
	 */
	@Test
	public void testGetLocalIp2 () throws Exception {
		System.out.println(getLocalIp());
		System.out.println(getLocalIp2());
		System.out.println(getLocalIp3());
	}
}
