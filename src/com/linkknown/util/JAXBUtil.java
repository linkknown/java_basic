package com.linkknown.util;

import java.io.Reader;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBUtil {

	public static <T> void objectToXml(T t, Writer writer) throws Exception {
		// 获取JAXB的上下文环境
		JAXBContext context = JAXBContext.newInstance(t.getClass());
		// 创建 Marshaller 实例
		Marshaller marshaller = context.createMarshaller();
		// 设置转换参数 -> 这里举例是告诉序列化器是否格式化输出
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// 将所需对象序列化 -> 该方法没有返回值
		marshaller.marshal(t, writer);
	}

	public static <T> T xmlToObject(T t, Reader reader) throws Exception {
        // 获取JAXB的上下文环境
        JAXBContext context = JAXBContext.newInstance(t.getClass());
        // 创建 UnMarshaller 实例
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // 将XML数据序列化 -> 该方法的返回值为Object基类，需要强转类型
        T object = (T) unmarshaller.unmarshal(reader);
        return object;
	}
}
