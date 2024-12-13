package com.sfass.bsamonitoring.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sfass.bsamonitoring.user.model.User;
import com.sfass.bsamonitoring.user.model.UserLoginDto;

@Mapper
public interface UserMapper {
	User loginUser(UserLoginDto userLoginDto);

	List<User> getUsers(Map<String, String> paramMap);

	void insertUser(User user);

	User getUser(UserLoginDto userLoginDto);

	void updateLogin(UserLoginDto userLoginDto);
}