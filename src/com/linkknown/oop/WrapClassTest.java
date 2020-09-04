package com.linkknown.oop;

import org.junit.jupiter.api.Test;

public class WrapClassTest {

	@Test
	public void convert () {
		// int <==> Integer
		// int <==> String
		// Integer <==> String
		
		// int => Integer
		Integer number = new Integer(10);
		// Integer => int
		int number2 = number.intValue();
		// int => String
		String number3 = String.valueOf(10);
		// String => int
		int number4 = Integer.parseInt("10");
		// Integer => String
		String number5 = number.toString();
		// String => Integer
		Integer number6 = new Integer("10");
	}

	
}
