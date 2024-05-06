package com.example.jwt.service;

import org.springframework.stereotype.Service;

import com.example.jwt.dto.AuthDto;
import com.example.jwt.dto.RequestLoginDto;
import com.example.jwt.dto.TokenDto;
import com.example.jwt.entity.UserEntity;
import com.example.jwt.exception.NotFoundUserException;
import com.example.jwt.repository.AuthRepository;
import com.example.jwt.util.HashUtil;
import com.example.jwt.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {
	
	private final AuthRepository authRepository;
	private final JwtUtil jwtUtil;
	private final HashUtil hashUtil;
	
	public AuthServiceImpl(AuthRepository authRepository, JwtUtil jwtUtil, HashUtil hashUtil) {
		this.authRepository = authRepository;
		this.jwtUtil = jwtUtil;
		this.hashUtil = hashUtil;
	}
	
	public AuthDto login(RequestLoginDto requestLoginDto) throws NotFoundUserException {
		
		// 1. requestLoginDto와 일치하는 User Entity
		UserEntity userEntity = authRepository.getUser(requestLoginDto);
		
		// 1-1. 일치하는 사용자가 없는 경우, NotFoundUserException 발생.
		if(userEntity == null) {
			throw new NotFoundUserException();
		}
		
		// 2. AccessToken과 RefreshToken을 발급.
		TokenDto accessToken = jwtUtil.generateToken(userEntity, "AccessToken");
		TokenDto refreshToken = jwtUtil.generateToken(userEntity, "RefreshToken");
		
		// 3. 발급한 토큰을 token_status 테이블에 저장.
		authRepository.saveToken(accessToken);
		authRepository.saveToken(refreshToken);
		
		// 4. AuthDto 설정.
		AuthDto authDto = new AuthDto();
		authDto.setAccessToken(accessToken.getToken());
		authDto.setRefreshToken(refreshToken.getToken());
		authDto.setMaxAge(refreshToken.getExpiration());
		
		return authDto;
	}

	@Override
	public void logout(String accessToken) {
		
		// 1. 토큰으로부터 사용자의 이메일을 추출.
		String userEmail = jwtUtil.getUserEmail(accessToken, "AccessToken");
		
		// 2. 해당 사용자의 활성화된 토큰을 모두 비활성화 처리.
		authRepository.setInvalid(userEmail);
		
	}

	@Override
	public TokenDto reGenerateToken(String refreshToken) {
		
		String userEmail = jwtUtil.getUserEmail(refreshToken, "RefreshToken");
		
		authRepository.setInvalid(userEmail, hashUtil.getDigest(refreshToken));
		
		UserEntity userEntity = authRepository.getUser(userEmail);
		TokenDto tokenDto = jwtUtil.generateToken(userEntity, "AccessToken");
		
		authRepository.saveToken(tokenDto);
		
		return tokenDto;
	}
}
