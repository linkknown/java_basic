package com.linkknown.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.Test;

/**
 * ������,���л��ͷ����л�
 * @author Administrator
 *
 */
public class ObjectStreamTest {

	/**
	 * ������,���л��ͷ����л�
	 * ���Զ��������
	 * @throws IOException
	 */
	@Test
	public void tsetObjectOutputStream () throws IOException {
		Address address = new Address();
		address.setProvince("����ʡ");
		address.setCity("�Ϸ���");
		address.setCounty("������");
		
		OutputStream out = new FileOutputStream("D:/io7.txt");
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(address);
		
		oos.close();
	}
	
	@Test
	public void tsetObjectInputStream () throws IOException, ClassNotFoundException {
		InputStream in = new FileInputStream("D:/io7.txt");
		ObjectInputStream ois = new ObjectInputStream(in );
		Object object = ois.readObject();
		if (object instanceof Address) {
			Address address = (Address) object;
			System.out.println(address);
		}
		
		ois.close();
	}
}
