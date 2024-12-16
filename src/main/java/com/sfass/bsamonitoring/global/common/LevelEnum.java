package com.sfass.bsamonitoring.global.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LevelEnum {
	GREEN("green"),
	YELLOW("yellow"),
	RED("red");

	private final String name;
}
