package com.linkknown.other;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

public class MD5Test {

    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
    
    @Test
    public void testMD5 () {
    	System.out.println(MD5Test.stringToMD5("helloworld"));
    	System.out.println(MD5Test.stringToMD5("helloworld"));
    }
}
