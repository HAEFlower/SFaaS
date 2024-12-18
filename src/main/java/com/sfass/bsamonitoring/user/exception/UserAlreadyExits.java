package com.sfass.bsamonitoring.user.exception;

import com.sfass.bsamonitoring.global.error.exception.CustomException;
import com.sfass.bsamonitoring.global.error.exception.ErrorCode;

public class UserAlreadyExits extends CustomException {
	public UserAlreadyExits() {
		super(ErrorCode.USER_ALREADY_EXITS);
	}
}
