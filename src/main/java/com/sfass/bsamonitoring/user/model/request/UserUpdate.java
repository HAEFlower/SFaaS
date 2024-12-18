package com.sfass.bsamonitoring.user.model.request;

import lombok.Data;

@Data
public class UserUpdate {
	private String requestEmpNo;
	private String targetEmpNo;
	private String newAuth;
}
