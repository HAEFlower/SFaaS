package com.sfass.bsamonitoring.production.process.exception;

import com.sfass.bsamonitoring.global.error.exception.CustomException;
import com.sfass.bsamonitoring.global.error.exception.ErrorCode;

public class ProcessNotFoundException extends CustomException {
	public ProcessNotFoundException() {
		super(ErrorCode.PROCESS_NOT_FOUND);
	}
}
