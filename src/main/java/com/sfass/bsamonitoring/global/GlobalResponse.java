package com.sfass.bsamonitoring.global;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.sfass.bsamonitoring.global.error.ErrorResponse;

public record GlobalResponse(boolean status, Object data, String timestamp) {
	public static GlobalResponse ok(Object data) {
		return new GlobalResponse(true, data, getNow());
	}

	public static GlobalResponse error(ErrorResponse errorResponse) {
		return new GlobalResponse(false, errorResponse, getNow());
	}

	private static String getNow() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(formatter);
	}
}