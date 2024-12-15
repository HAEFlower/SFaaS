package com.sfass.bsamonitoring.global.Util;

public class MathUtil {
	public static double roundToTwoDecimalPlaces(double value) {
		return Math.round(value * 100.0) / 100.0;
	}
}
