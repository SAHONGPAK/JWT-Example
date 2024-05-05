package com.example.jwt.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.jwt.dto.TokenDto;
import com.example.jwt.entity.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * 토큰 발급 및 검증을 위한 클래스.
 */
@Component
public class JwtUtil {
	
	private final HashUtil hashUtil;
	
	public JwtUtil(HashUtil hashUtil) {
		this.hashUtil = hashUtil;
	}
	
	private final long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 15; // 15분
	private final long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 4; // 4시간
	
	@Value("${jwt.secretkey.accesstoken}")
	private String accessTokenSecretKey;
	
	@Value("${jwt.secretkey.refreshtoken}")
	private String refreshTokenSecretKey;

	
	private SecretKey getSecretKey(String type) {
		// AccessToken과 RefreshToken을 서로 다른 키를 이용하여 발급.
		// AccessToken과 RefreshToken을 서로 변경하여 인증 시도를 방지.
		
		if(type.equals("AccessToken")) {
			return Keys.hmacShaKeyFor(accessTokenSecretKey.getBytes());
		}
		else if(type.equals("RefreshToken")) {
			return Keys.hmacShaKeyFor(refreshTokenSecretKey.getBytes());
		}
		
		return null;
	}
	
	private long getExpiration(long issuedAt, String type) {
		if(type.equals("AccessToken")) {
			return issuedAt + ACCESS_TOKEN_EXPIRE_TIME;
		}
		else if(type.equals("RefreshToken")) {
			return issuedAt + REFRESH_TOKEN_EXPIRE_TIME;
		}
		
		return 0L;
	}
	
	public TokenDto generateToken(UserEntity userEntity, String type) {
		
		// 발급 시간과 만료 시간을 계산.
		long issuedAt = System.currentTimeMillis();
		long expiration = getExpiration(issuedAt, type);
		
		String token = Jwts.builder()
				.claim("userEmail", userEntity.getEmail())
				.claim("userRole", userEntity.getRole())
				.issuedAt(new Date(issuedAt))
				.expiration(new Date(expiration))
				.signWith(getSecretKey(type))
				.compact();
		
		TokenDto tokenDto = new TokenDto();
		tokenDto.setUserEmail(userEntity.getEmail());
		tokenDto.setToken(token);
		tokenDto.setHashedToken(hashUtil.getDigest(token));
		tokenDto.setIssuedAt(issuedAt);
		tokenDto.setExpiration(expiration);
		
		return tokenDto;
	}

}
