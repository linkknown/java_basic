package com.linkknown.annotation;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * �Զ���ע�����
 * @author Administrator
 *
 */
public class CustomAnnotationTest {

	@Test
	public void testCustomAnnotation () throws FileNotFoundException, IOException {
		CommonConfig config = CommonConfigUtil.getInstance();
		System.out.println(config.getUserName());
		System.out.println(config.getPassWd());
	}
}
