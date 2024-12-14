package com.sfass.bsamonitoring.global;

import java.time.LocalDateTime;

import com.sfass.bsamonitoring.global.error.ErrorResponse;

public record GlobalResponse(boolean status, Object data, LocalDateTime timestamp) {
	public static GlobalResponse ok(Object data) {
		return new GlobalResponse(true, data, LocalDateTime.now());
	}

	public static GlobalResponse error(ErrorResponse errorResponse) {
		return new GlobalResponse(false, errorResponse, LocalDateTime.now());
	}
}