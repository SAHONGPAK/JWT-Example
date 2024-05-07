package com.example.jwt.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;


/**
 * 평문을 SHA-512로 처리하는 클래스.
 */
@Component
public class HashUtil {
	
	public String getDigest(String plainText) {
		
		MessageDigest messageDigest = null;
		
		try {
			messageDigest = MessageDigest.getInstance("SHA-512");
			messageDigest.reset();
			messageDigest.update(plainText.getBytes("UTF-8"));
		}catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return String.format("%0128x", new BigInteger(1, messageDigest.digest()));
	}
}
