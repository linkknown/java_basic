package com.linkknown.oop;

import org.junit.jupiter.api.Test;

import com.linkknown.oop.calculator.Calculator;
import com.linkknown.oop.calculator.NewScienceCalculator;
import com.linkknown.oop.calculator.OldScienceCalculator;
import com.linkknown.oop.calculator.OrdinaryCalculator;
import com.linkknown.oop.calculator.ScienceCalculator;

public class OOPTest2 {

	/**
	 * 测试 static
	 * 综合练习:读取配置文件
	 */
	@Test
	public void testReadFile () {
		// 修饰静态属性
		// 修饰静态方法
		// 修饰静态代码块
		ConfigFile configUtil = ConfigFile.getInstance();
		System.out.println(configUtil.getUserName());
		System.out.println(configUtil.getPassword());
	}
	
	/**
	 * 测试 final、abstract、interface 和 可变参数
	 */
	@Test
	public void testOOP () {
		Calculator ordinaryCalculator = new OrdinaryCalculator();
		int result = ordinaryCalculator.getOperator("+").eval(1, 1);
		System.out.println(result);
		ordinaryCalculator.showDesc(true);
		ordinaryCalculator.showDesc();
		
		ScienceCalculator newScienceCalculator = new NewScienceCalculator();
		int result2 = newScienceCalculator.getOperator("^").eval(2,8);
		System.out.println(result2);
		newScienceCalculator.showDesc(true);
		newScienceCalculator.showDesc();
		
		System.out.println("5 个老式科学计算器价格是：" + newScienceCalculator.getPrice() * 10);
		
		Calculator oldScienceCalculator = new OldScienceCalculator();
		int result3 = oldScienceCalculator.getOperator("^").eval(2,16);
		System.out.println(result3);
		oldScienceCalculator.showDesc(true);
		oldScienceCalculator.showDesc();
	}
}
