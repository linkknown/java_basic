package com.linkknown.generic;

/**
 * 泛型接口
 */
public interface Generator<T> {

	/**
	 * 按照一定规则生成下一个数据的函数
	 * @return
	 */
	T next();
}
