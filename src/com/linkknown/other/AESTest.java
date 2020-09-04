package com.linkknown.other;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.junit.jupiter.api.Test;

public class AESTest {


	/**
	 * AES加密+Base64转码
	 * 
	 * @param decryptStr 明文（16进制）
	 * @param key  密钥
	 * @return
	 */
	public static String encrypt(String decryptStr, String key) {
		byte[] keyb = null;
		try {
			keyb = key.getBytes("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} // 明文
		SecretKeySpec sKeySpec = new SecretKeySpec(keyb, "AES");
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		try {
			cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		byte[] buf = null;
		String encryptStr = "";
		try {
			buf = cipher.doFinal(decryptStr.getBytes("utf-8"));
			// byte加密后
			encryptStr = Base64.getEncoder().encodeToString(buf);// 密文用base64加密
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
 
		return encryptStr;
	}
 
	/**
	 * Base64解码 + AES解码
	 * 
	 * @param data 密文 （16进制）
	 * @param key  密钥
	 * @return
	 */
	public static String decrypt(String encryptStr, String key){
		byte[] keyb = null;
		try {
			keyb = key.getBytes("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		byte[] buf = Base64.getDecoder().decode(encryptStr);
		SecretKeySpec sKeySpec = new SecretKeySpec(keyb, "AES");
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		try {
			cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		byte[] buf2 = null;
		String decryptStr = "";
		try {
			buf2 = cipher.doFinal(buf);
			// byte加密后
			decryptStr = new String(buf2,"utf-8");
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decryptStr;
	}
	
	//length用户要求产生字符串的长度
	 public static String getRandomKey(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }
	
	@Test
	public void testAES() {
		String content = "我喜欢编程";
//		String password = "1234567890123456";
		String password = getRandomKey(16);
		System.out.println(password);
		System.out.println("加密之前：" + content);

		// 加密
		String encrypt = AESTest.encrypt(content, password);
		System.out.println("加密后的内容：" + AESTest.encrypt(content, password));

		// 解密
		String decrypt = AESTest.decrypt(encrypt, password);
		System.out.println("解密后的内容：" + decrypt);
	}
}
