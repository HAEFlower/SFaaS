package com.sfass.bsamonitoring.user.model;

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
}
