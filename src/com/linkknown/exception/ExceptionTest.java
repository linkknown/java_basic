package com.linkknown.exception;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * �쳣����
 * 
 * @author Administrator
 *
 */
public class ExceptionTest {

	public static void main(String[] args) {
		testError();
	}
	
	/**
	 * ���Դ���
	 * ���ǿ���ͨ��ָ��-Xmx���������ٵ�ģ���OutOfMemoryError�Ĵ���,-Xmxָ������JVM�������ڴ�,�����ֵ��С,�ǳ����׾ͻ����OutOfMemoryError�Ĵ���
	 * ���������������-Xmx1m
	 */
	private static void testError() {
		List<String> lst = new ArrayList<>();
		// �洢 100 w ������
		for (int i = 0; i < 1000000; i++) {
			lst.add("helloworld");
		}
		System.out.println(lst.size());
	}
	
	/**
	 * �����쳣
	 */
	@Test
	private void testException() {
		System.out.println(10 / 0);
	}
	
	/*************************************** ģ��Ǽ���쳣 ******************************************/ 
	/**
	 * ģ��Ǽ���쳣
	 * @param str
	 * @return
	 */
	private static String toUpper(String str) {
		return str.toUpperCase();
	}
	
	/**
	 * ���Կ�ָ���쳣
	 */
	@Test
	public void testNullPointerException () {
		System.out.println(toUpper(null));
	}
	
	/**
	 * ���������±�Խ���쳣
	 */
	@Test
	public void testArrayIndexOutOfBoundsException () {
		int[] intArr = new int[2];
		intArr[2] = 10;
	}
	
	/**
	 * ���������쳣
	 */
	@Test
	public void testArithmeticException () {
		int i = 10 / 0;
	}
	
	
	/*************************************** ģ�����쳣 ******************************************/


	/**
	 * ���Լ���쳣
	 */
	@Test
	public  void testParseException () {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = dateFormat.parse("helloworld");
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���Լ���쳣
	 */
	@Test
	public void testUnknownHostException() {
		InetAddress ia = null;
		try {
			ia = InetAddress.getLocalHost();

			String localname = ia.getHostName();
			String localip = ia.getHostAddress();
			System.out.println("���������ǣ�" + localname);
			System.out.println("������ip�� ��" + localip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���� try catch finally
	 */
	@Test
	public void testTryCatchFinally() {
		FileWriter fw = null;
        try{
            //���ܻ�����쳣�Ĵ���
            fw = new FileWriter("D:\\helloworld.txt",true);
            for (int i = 0; i <10 ; i++) {
                fw.write("HelloWorld"+i+"\r\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            //һ����ָ���Ĵ���
            //��������ʧ����,fw��Ĭ��ֵ����null,null�ǲ��ܵ��÷�����,���׳�NullPointerException,��Ҫ����һ���ж�,����null�ڰ���Դ�ͷ�
            if(fw!=null){
                try {
                    //fw.close���������׳���IOException�쳣����,�������Ǿ͵Ĵ�������쳣����,Ҫôthrows,Ҫôtry catch
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
	}
	
	
	/*************************************** ��鴦�� ******************************************/ 
	/**
	 * try catch ���񲢴����쳣
	 */
	@Test
	public void testException1 () {
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
	 * try finally ʹ�ó���
	 */
	@Test
	public void testTryFinally () {
		ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					putData(threadLocal);
				} finally {
					System.out.println("ִ���� finally ���, threadLocal ������̱߳���ֵΪ��" + threadLocal.get());
					// ��Ҫ�� finally ���������ͷ��̱߳��������ͷſ��ܻ�����ڴ�й©
					threadLocal.remove();
				}
			}
		});
		thread.start();
	}

	public static void putData (ThreadLocal<Integer> threadLocal) {
		threadLocal.set(10);
		// �˴����׳�����ʱ�쳣
		threadLocal.set(10 / 0);
	}

	/**
	 * throws �׳��쳣
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
	 * try catch ���񲢴����쳣
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
	 * �׳��쳣
	 * @throws UnknownHostException 
	 */
	private static String getLocalIp () throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
	
	/**
	 * ͬʱ�׳�����쳣
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
	 * �����쳣�������׳�
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
	 * �����쳣�������׳�
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
	 * �׳�����쳣
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
	 * �׳������쳣
	 * @throws Exception
	 */
	@Test
	public void testGetLocalIp2 () throws Exception {
		System.out.println(getLocalIp());
		System.out.println(getLocalIp2());
		System.out.println(getLocalIp3());
	}
}
