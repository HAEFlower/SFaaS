package com.sfass.bsamonitoring.global.common;

import java.util.Arrays;

import javax.management.BadAttributeValueExpException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
	RUNNING("RUNNING"),
	STOPPED("STOPPED"),
	MAINTENANCE("MAINTENANCE");

	private final String name;
	// TODO: 에러 클래스 정의 해주기
	public static StatusEnum from(final String name) {
		return Arrays.stream(StatusEnum.values())
			.filter(type -> type.getName().equals(name))
			.findFirst()
			.orElseThrow(RuntimeException::new);
	}
}
