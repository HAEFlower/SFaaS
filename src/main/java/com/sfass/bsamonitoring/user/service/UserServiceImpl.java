package com.sfass.bsamonitoring.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.sfass.bsamonitoring.user.exception.UserAlreadyExits;

import com.sfass.bsamonitoring.user.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sfass.bsamonitoring.global.common.userConstants.UserConstants;
import com.sfass.bsamonitoring.user.mapper.UserMapper;
import com.sfass.bsamonitoring.user.model.User;
import com.sfass.bsamonitoring.user.model.UserLoginDto;
import com.sfass.bsamonitoring.user.model.UserRegisterDto;
import com.sfass.bsamonitoring.user.model.request.UserUpdate;
import com.sfass.bsamonitoring.user.model.response.UserUpdateResponse;

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
	public List<User> getUsers(Map<String, Object> paramMap) {

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
		user.setPosition("none");
		user.setProductionLine(userRegisterDto.getProductionLine());
		user.setProcessLine(userRegisterDto.getProcessLine());
		user.setLastLoginTime(LocalDateTime.now());
		user.setLoginStatus(false);
		user.setEmpNo(generateEmpNo());
		user.setAuth(userRegisterDto.getAuth());

		User temp = userMapper.getUserById(user.getUserId());
		if (temp != null) {
			throw new UserAlreadyExits();
		}

		userMapper.insertUser(user);
		UserLoginDto userLoginDto = new UserLoginDto();
		userLoginDto.setUserId(user.getUserId());
		userLoginDto.setUserPwd(user.getUserPwd());
		User result = userMapper.getUser(userLoginDto);

		return result;
	}

	@Override
	public UserUpdateResponse updateUserPosition(UserUpdate update) {

		User requester = userMapper.getUserByEmpNo(update.getRequestEmpNo());
		User targetUser = userMapper.getUserByEmpNo(update.getTargetEmpNo());

		if (requester == null || targetUser == null) {
			throw new UserNotFoundException();
		}

		if (!requester.getAuth().isHigherAuthority(targetUser.getAuth())) {
			return new UserUpdateResponse(UserConstants.USER_AUTH_UPDATE_FAIL);
		}

		userMapper.updateAuth(update);
		return new UserUpdateResponse(UserConstants.USER_AUTH_UPDATE_SUCCESS);
	}

	@Override
	public UserUpdateResponse deleteUser(UserUpdate delete) {
		User requester = userMapper.getUserByEmpNo(delete.getRequestEmpNo());
		User targetUser = userMapper.getUserByEmpNo(delete.getTargetEmpNo());

		if (requester == null || targetUser == null) {
			return new UserUpdateResponse(UserConstants.USER_DELETE_FAIL);
		}

		if (requester.getEmpNo().equals(targetUser.getEmpNo())) {
			return new UserUpdateResponse(UserConstants.USER_DELETE_FAIL);
		}

		if (!requester.getAuth().isHigherAuthority(targetUser.getAuth())) {
			return new UserUpdateResponse(UserConstants.USER_DELETE_FAIL);
		}

		userMapper.deleteUser(delete.getTargetEmpNo());
		return new UserUpdateResponse(UserConstants.USER_DELETE_SUCCESS);
	}

	private String generateEmpNo() {
		String empNoPrefix = "EMP";
		long timestamp = System.currentTimeMillis() % 1000000;
		return String.format("%s%06d", empNoPrefix, timestamp);
	}

}
