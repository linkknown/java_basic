package com.linkknown.generic;

/**
 * �������ӿ�
 * @author Administrator
 *
 * @param <T>
 */
public interface Generator<T> {

	/**
	 * ����һ������������һ������
	 * @return
	 */
	T next();
}
