package com.example.jwt.service;

import com.example.jwt.dto.RequestSignUpDto;
import com.example.jwt.dto.UserDto;
import com.example.jwt.exception.InvalidEmailException;

public interface UserService {

	UserDto myPage(String accessToken);

	void checkEmail(String userEmail) throws InvalidEmailException;

	void signUp(RequestSignUpDto requestSignUpDto);

}
