package com.example.jwt.repository;

import com.example.jwt.dto.RequestSignUpDto;
import com.example.jwt.entity.UserEntity;

public interface UserRepository {

	UserEntity getUser(String userEmail);

	int getEmail(String userEmail);

	void saveUser(RequestSignUpDto requestSignUpDto);

}
