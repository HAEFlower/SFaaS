package com.sfass.bsamonitoring.user.service;

import java.util.List;
import java.util.Map;

import com.sfass.bsamonitoring.user.model.User;
import com.sfass.bsamonitoring.user.model.UserLoginDto;
import com.sfass.bsamonitoring.user.model.UserRegisterDto;
import com.sfass.bsamonitoring.user.model.request.UserUpdate;
import com.sfass.bsamonitoring.user.model.response.UserUpdateResponse;

public interface UserService {
	User loginUser(UserLoginDto userLoginDto);

	List<User> getUsers(Map<String, Object> paramMap);

	User registerUser(UserRegisterDto userRegisterDto);

	UserUpdateResponse updateUserPosition(UserUpdate update);
}
