package com.sfass.bsamonitoring.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sfass.bsamonitoring.user.mapper.UserMapper;
import com.sfass.bsamonitoring.user.model.User;
import com.sfass.bsamonitoring.user.model.UserLoginDto;
import com.sfass.bsamonitoring.user.model.UserRegisterDto;

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
		userMapper.updateLogin(userLoginDto);
		user.setLastLoginTime(LocalDateTime.now());
		user.setLoginStatus(true);
		return user;
	}

	@Override
	public List<User> getUsers(Map<String, String> paramMap) {

		List<User> result = userMapper.getUsers(paramMap);

		return result;
	}

	@Override
	public User registerUser(UserRegisterDto userRegisterDto) {
		User user = new User();
		user.setUserId(userRegisterDto.getUserId());
		user.setUserPwd(userRegisterDto.getPassword());
		user.setUserName(userRegisterDto.getUserName());
		user.setEmail(userRegisterDto.getEmail());
		user.setDepartment(userRegisterDto.getDepartment());
		user.setPosition(userRegisterDto.getPosition());
		user.setProductionLine(userRegisterDto.getProductionLine());
		user.setProcessLine(userRegisterDto.getProcessLine());
		user.setLastLoginTime(LocalDateTime.now());
		user.setLoginStatus(false);
		user.setEmpNo(generateEmpNo());
		user.setAuth("User");

		userMapper.insertUser(user);
		UserLoginDto userLoginDto = new UserLoginDto();
		userLoginDto.setUserId(user.getUserId());
		userLoginDto.setUserPwd(user.getUserPwd());
		User result = userMapper.getUser(userLoginDto);

		return result;
	}

	private String generateEmpNo() {
		String empNoPrefix = "EMP";
		long timestamp = System.currentTimeMillis() % 1000000;
		return String.format("%s%06d", empNoPrefix, timestamp);
	}

}
