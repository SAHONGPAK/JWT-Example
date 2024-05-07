package com.example.jwt.util;

import java.util.regex.Pattern;

public class FormatUtil {
	
	private static Pattern pattern;

	
	public static boolean isValidEmail(String userEmail) {
		
		// 이메일 양식이 맞는지 검증.
		pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
		
		return pattern.matcher(userEmail).find();
	}

}
