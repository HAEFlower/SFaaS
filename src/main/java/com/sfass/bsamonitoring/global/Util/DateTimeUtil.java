package com.sfass.bsamonitoring.global.Util;

import java.time.YearMonth;

public class DateTimeUtil {
	public static long calculateTotalMinutesInMonth(int year, int month) {
		YearMonth yearMonth = YearMonth.of(year, month);
		int daysInMonth = yearMonth.lengthOfMonth();
		return daysInMonth * 24L * 60L; // 일 * 시간 * 분
	}
}
