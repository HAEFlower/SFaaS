package com.sfass.bsamonitoring.global.Util;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
	public static long calculateTotalMinutesInMonth(int year, int month) {
		YearMonth yearMonth = YearMonth.of(year, month);
		int daysInMonth = yearMonth.lengthOfMonth();
		return daysInMonth * 24L * 60L; // 일 * 시간 * 분
	}

	public static String makeDateFormat(LocalDateTime time) {
		if (time == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return time.format(formatter);
	}
}
