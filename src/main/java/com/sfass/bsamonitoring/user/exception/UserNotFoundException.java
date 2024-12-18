package com.sfass.bsamonitoring.user.exception;

import com.sfass.bsamonitoring.global.error.exception.CustomException;
import com.sfass.bsamonitoring.global.error.exception.ErrorCode;

public class UserNotFoundException extends CustomException {
	public UserNotFoundException() {
		super(ErrorCode.USER_NOT_FOUND);
	}
}
