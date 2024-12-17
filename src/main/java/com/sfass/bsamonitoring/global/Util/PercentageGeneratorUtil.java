package com.sfass.bsamonitoring.global.Util;

import java.text.DecimalFormat;
import java.util.Random;

public class PercentageGeneratorUtil {
	// 5~20퍼센트 사이의 랜덤 값을 생성하는 함수
	public static double generatePercentage() {
		Random random = new Random();
		// 5.00 ~ 20.00 사이의 값 생성
		return 5.0 + (random.nextDouble() * 15.0);
	}

	// 퍼센트 값을 "##.##%" 형식의 문자열로 변환하는 함수
	public static String formatPercentage(double percentage) {
		DecimalFormat df = new DecimalFormat("##.##%");
		return df.format(percentage / 100); // 백분율로 변환
	}
}
