package com.example.jwt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.jwt.entity.UserEntity;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM user WHERE email=#{userEmail}")
	UserEntity selectByUserEmail(String userEmail);

}
