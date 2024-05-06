package com.example.jwt.repository;

import org.springframework.stereotype.Repository;

import com.example.jwt.dto.RequestLoginDto;
import com.example.jwt.dto.TokenDto;
import com.example.jwt.entity.UserEntity;
import com.example.jwt.mapper.AuthMapper;
import com.example.jwt.util.HashUtil;

@Repository
public class AuthRepositoryImpl implements AuthRepository {

	private final AuthMapper authMapper;
	private final HashUtil hashUtil;
	
	public AuthRepositoryImpl(AuthMapper authMapper, HashUtil hashUtil) {
		this.authMapper = authMapper;
		this.hashUtil = hashUtil;
	}
	
	@Override
	public UserEntity getUser(RequestLoginDto requestLoginDto) {
		requestLoginDto.setUserPassword(hashUtil.getDigest(requestLoginDto.getUserPassword()));
		return authMapper.selectByRequestLoginDto(requestLoginDto);
	}

	@Override
	public int saveToken(TokenDto tokenDto) {
		return authMapper.insertTokenDto(tokenDto);
	}

	@Override
	public void setInvalid(String userEmail) {
		authMapper.updateValidTokenToInvalidByUserEmail(userEmail);
	}

	@Override
	public UserEntity getUser(String userEmail) {
		return authMapper.selectByUserEmail(userEmail);
	}

	@Override
	public void setInvalid(String userEmail, String hashedRefreshToken) {
		authMapper.updateValidTokenToInvalidByUserEmailAndRefreshToken(userEmail, hashedRefreshToken);
	}

	@Override
	public boolean getValidStatusToken(String token) {
		token = hashUtil.getDigest(token);
		return authMapper.selectValidStatusByToken(token);
	}

}
