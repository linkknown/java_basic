package com.linkknown.exception;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

/**
 * ����쳣����
 * 
 * @author Administrator
 *
 */
public class CheckedExceptionTest {

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
	 * ���Լ���쳣 IOException
	 */
	@Test
	public void testIOException() {
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
	
}
