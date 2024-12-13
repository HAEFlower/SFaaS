package com.sfass.bsamonitoring.global;

import java.time.LocalDateTime;

public record GlobalResponse(boolean status, Object data, LocalDateTime timestamp) {
	public static GlobalResponse ok(Object data) {
		return new GlobalResponse(true, data, LocalDateTime.now());
	}

	public static GlobalResponse error(String message) {
		return new GlobalResponse(false, message, LocalDateTime.now());
	}
}