package com.sfass.bsamonitoring.factory.environment.model;

import com.sfass.bsamonitoring.global.common.LevelEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FactoryEnvResponse {
	private Double currTemperature;
	private Double currHumidity;
	private Double currAirCondition;
	private Double currAirQuality;
	private Double targetTemperature;
	private Double targetHumidity;
	private Double targetAirCondition;
	private Double targetAirQuality;
	private LevelEnum tempLevel;
	private LevelEnum humLevel;
	private LevelEnum airConLevel;
	private LevelEnum airQuaLevel;

	public static FactoryEnvResponse from (FactoryStats stats) {
		return new FactoryEnvResponse(
			stats.getCurrTemperature(),
			stats.getCurrHumidity(),
			stats.getCurrAirCondition(),
			stats.getCurrAirQuality(),
			stats.getTargetTemperature(),
			stats.getTargetHumidity(),
			stats.getTargetAirCondition(),
			stats.getTargetAirQuality(),
			getLevel(stats.getCurrTemperature(), stats.getTargetTemperature()),
			getLevel(stats.getCurrHumidity(), stats.getTargetHumidity()),
			getLevel(stats.getCurrAirCondition(), stats.getTargetAirCondition()),
			getLevel(stats.getCurrAirQuality(), stats.getTargetAirQuality())
		);
	}

	private static LevelEnum getLevel(Double curr, Double target) {
		if (target == null || curr == null) {
			return LevelEnum.GREEN; // 기본값 설정 또는 예외 처리
		}

		double difference = Math.abs(curr - target);
		double percentDifference = (difference / target) * 100;

		if (percentDifference >= 15) {
			return LevelEnum.RED;
		} else if (percentDifference >= 10) {
			return LevelEnum.YELLOW;
		} else {
			return LevelEnum.GREEN;
		}
	}

}
