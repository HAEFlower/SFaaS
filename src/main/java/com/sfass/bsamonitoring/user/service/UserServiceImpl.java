package com.sfass.bsamonitoring.user.service;

import org.springframework.stereotype.Service;

import com.sfass.bsamonitoring.user.mapper.UserMapper;
import com.sfass.bsamonitoring.user.model.User;
import com.sfass.bsamonitoring.user.model.UserLoginDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;

	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public User loginUser(UserLoginDto userLoginDto) {

		User user = userMapper.loginUser(userLoginDto);

		if (user != null) {
			log.error(user.toString());
		}

		return user;
	}
}