package com.sfass.bsamonitoring.user.model;

import com.sfass.bsamonitoring.user.userEnum.UserAuth;

import lombok.Data;

@Data
public class UserRegisterDto {
	private String userId;
	private String password;
	private String userName;
	private String department;
	private String position;
	private String productionLine;
	private String processLine;
	private String email;
	private UserAuth auth;
}
