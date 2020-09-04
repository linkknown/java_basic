package com.linkknown.string;

import java.util.UUID;

import org.junit.jupiter.api.Test;

/**
 * Œ®“ª–‘ id ≤‚ ‘
 * @author Administrator
 *
 */
public class UUIDTest {

	@Test
	public void testUUID () {
		UUID uuid = UUID.randomUUID();
		String str = UUID.randomUUID().toString(); 
		
		System.out.println(str);
	}
}
