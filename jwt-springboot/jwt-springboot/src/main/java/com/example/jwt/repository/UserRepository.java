package com.example.jwt.repository;

import com.example.jwt.entity.UserEntity;

public interface UserRepository {

	UserEntity getUser(String userEmail);

}
