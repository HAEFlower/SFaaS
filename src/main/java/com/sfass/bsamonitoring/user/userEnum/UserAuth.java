package com.sfass.bsamonitoring.user.userEnum;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserAuth {
	ADMIN(0),
	OPERATOR(1),
	MONITOR(2),
	NONE(3);

	private final Integer level;

	public boolean isHigherAuthority(UserAuth position2) {
		if (position2 == null) {
			return false;
		}

		return this.level <= position2.level;
	}

}
