package com.sfass.bsamonitoring.global.error.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	// Common
	QUERY_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "올바르지 않은 쿼리 타입 입니다."),
	QUERY_PARAM_INVALID(HttpStatus.BAD_REQUEST, "올바르지 않은 쿼리 파라미터 값입니다."),
	QUERY_PARAM_NOT_FOUND(HttpStatus.BAD_REQUEST, "쿼리 파라미터가 존재하지 않습니다."),

	// User
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
	USER_ALREADY_EXITS(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다."),

	// Auth
	PERMISSION_DENIED(HttpStatus.FORBIDDEN, "권한이 거부되었습니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 문제가 발생했습니다. 관리자에게 문의해주세요."),

	// ProductionLine
	PRODUCTION_LINE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 생산라인입니다."),

	// Process
	PROCESS_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 공정입니다."),

	// Inventory
	INVENTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 재고 정보입니다."),
	;
	private final HttpStatus httpStatus;
	private final String message;
}
