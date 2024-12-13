package com.sfass.bsamonitoring.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sfass.bsamonitoring.user.model.User;
import com.sfass.bsamonitoring.user.model.UserLoginDto;

@Mapper
public interface UserMapper {
	User loginUser(UserLoginDto userLoginDto);
}