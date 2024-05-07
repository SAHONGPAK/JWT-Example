package com.example.jwt.repository;

import org.springframework.stereotype.Repository;

import com.example.jwt.dto.RequestSignUpDto;
import com.example.jwt.entity.UserEntity;
import com.example.jwt.mapper.UserMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private final UserMapper userMapper;
	
	public UserRepositoryImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Override
	public UserEntity getUser(String userEmail) {
		return userMapper.selectByUserEmail(userEmail);
	}

	@Override
	public int getEmail(String userEmail) {
		return userMapper.selectCountByUserEmail(userEmail);
	}

	@Override
	public void saveUser(RequestSignUpDto requestSignUpDto) {
		userMapper.insertRequestSignUpDto(requestSignUpDto);
	}

}
