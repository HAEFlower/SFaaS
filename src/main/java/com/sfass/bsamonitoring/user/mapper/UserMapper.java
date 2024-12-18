package com.sfass.bsamonitoring.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sfass.bsamonitoring.user.model.User;
import com.sfass.bsamonitoring.user.model.UserLoginDto;
import com.sfass.bsamonitoring.user.model.request.UserUpdate;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
	User loginUser(UserLoginDto userLoginDto);

	List<User> getUsers(Map<String, Object> paramMap);

	void insertUser(User user);

	User getUser(UserLoginDto userLoginDto);

	void updateLogin(UserLoginDto userLoginDto);

	User getUserByEmpNo(String userEmpNo);

	void updateAuth(UserUpdate update);

	User getUserById(@Param("userId") String userId);

	void deleteUser(String empNo);
}