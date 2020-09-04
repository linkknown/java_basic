package com.linkknown.util;

import java.io.Reader;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBUtil {

	public static <T> void objectToXml(T t, Writer writer) throws Exception {
		// ��ȡJAXB�������Ļ���
		JAXBContext context = JAXBContext.newInstance(t.getClass());
		// ���� Marshaller ʵ��
		Marshaller marshaller = context.createMarshaller();
		// ����ת������ -> ��������Ǹ������л����Ƿ��ʽ�����
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// ������������л� -> �÷���û�з���ֵ
		marshaller.marshal(t, writer);
	}

	public static <T> T xmlToObject(T t, Reader reader) throws Exception {
        // ��ȡJAXB�������Ļ���
        JAXBContext context = JAXBContext.newInstance(t.getClass());
        // ���� UnMarshaller ʵ��
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // ��XML�������л� -> �÷����ķ���ֵΪObject���࣬��Ҫǿת����
        T object = (T) unmarshaller.unmarshal(reader);
        return object;
	}
}
