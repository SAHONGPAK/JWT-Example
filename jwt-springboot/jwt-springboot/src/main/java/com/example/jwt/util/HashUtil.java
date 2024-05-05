package com.example.jwt.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;


@Component
public class HashUtil {
	
	public String getDigest(String plainText) {
		
		StringBuilder sb = new StringBuilder();
		
		try {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			
			messageDigest.update(plainText.getBytes("UTF-8"));
			
			byte[] byteDigest = messageDigest.digest();
			
			
			for(int index = 0; index < byteDigest.length; index++) {
				sb.append(Integer.toString((byteDigest[index] & 0xFF) + 0x100, 16).substring(1));
			}
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			return null;
		}
		
		return sb.toString();
	}
}
