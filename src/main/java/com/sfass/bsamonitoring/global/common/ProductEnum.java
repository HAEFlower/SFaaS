package com.sfass.bsamonitoring.global.common;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductEnum {
	P2P144S("2P144S"),
	P2P180S("2P180S"),
	P2P192S("2P192S"),
	P3P144S("3P144S");

	private final String name;

	// TODO: 에러 클래스 정의 해주기
	public static ProductEnum from(final String name) {
		return Arrays.stream(ProductEnum.values())
			.filter(type -> type.getName().equals(name))
			.findFirst()
			.orElseThrow(RuntimeException::new);
	}
}
