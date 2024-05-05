package com.example.jwt.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.jwt.dto.RequestLoginDto;
import com.example.jwt.dto.TokenDto;
import com.example.jwt.entity.UserEntity;

@Mapper
public interface AuthMapper {

	@Select("SELECT * FROM user WHERE email=#{userEmail} AND password=#{userPassword}")
	UserEntity selectByRequestLoginDto(RequestLoginDto requestLoginDto);

	@Insert("INSERT INTO token_status VALUES(#{userEmail}, #{hashedToken}, #{dateExpiration}, true)")
	int insertTokenDto(TokenDto tokenDto);

	
}