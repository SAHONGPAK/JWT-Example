package com.example.jwt.repository;

import org.springframework.stereotype.Repository;

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

}
