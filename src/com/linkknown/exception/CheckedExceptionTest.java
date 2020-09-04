package com.linkknown.exception;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

/**
 * 检查异常测试
 * 
 * @author Administrator
 *
 */
public class CheckedExceptionTest {

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
	
}
