package com.example.jwt.service;

import com.example.jwt.dto.AuthDto;
import com.example.jwt.dto.RequestLoginDto;
import com.example.jwt.exception.NotFoundUserException;

public interface AuthService {
	AuthDto login(RequestLoginDto requestLoginDto) throws NotFoundUserException;

	void logout(String accessToken);
}
