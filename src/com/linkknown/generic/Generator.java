package com.linkknown.generic;

/**
 * 生成器接口
 * @author Administrator
 *
 * @param <T>
 */
public interface Generator<T> {

	/**
	 * 按照一定规则生成下一个函数
	 * @return
	 */
	T next();
}
