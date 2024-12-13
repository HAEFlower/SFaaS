package com.sfass.bsamonitoring.user.service;

import com.sfass.bsamonitoring.user.model.User;
import com.sfass.bsamonitoring.user.model.UserLoginDto;

public interface UserService {
	User loginUser(UserLoginDto userLoginDto);
}
