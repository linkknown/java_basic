package com.linkknown.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * �Զ����ȡ�����ļ�ע��
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface ReadConfig {

	// ע�������Ҳ������Ա������ע��ֻ�г�Ա������û�з�����ע��ĳ�Ա������ע��Ķ������ԡ����βεķ�����
	// ��ʽ���������䷽���������˸ó�Ա���������֣��䷵��ֵ�����˸ó�Ա����������
	String value();
}
