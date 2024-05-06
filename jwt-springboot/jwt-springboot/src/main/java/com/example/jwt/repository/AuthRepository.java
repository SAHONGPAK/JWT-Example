package com.example.jwt.repository;

import com.example.jwt.dto.RequestLoginDto;
import com.example.jwt.dto.TokenDto;
import com.example.jwt.entity.UserEntity;

public interface AuthRepository {

	UserEntity getUser(RequestLoginDto requestLoginDto);

	int saveToken(TokenDto accessToken);

	void setInvalid(String userEmail);

	UserEntity getUser(String userEmail);

	void setInvalid(String userEmail, String refreshToken);
}
