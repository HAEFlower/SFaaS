package com.sfass.bsamonitoring.user.service;

import java.util.List;
import java.util.Map;

import com.sfass.bsamonitoring.user.model.User;
import com.sfass.bsamonitoring.user.model.UserLoginDto;
import com.sfass.bsamonitoring.user.model.UserRegisterDto;

public interface UserService {
	User loginUser(UserLoginDto userLoginDto);
	List<User> getUsers(Map<String, String> paramMap);

	User registerUser(UserRegisterDto userRegisterDto);
}