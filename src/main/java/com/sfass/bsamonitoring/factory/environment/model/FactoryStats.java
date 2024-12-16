package com.sfass.bsamonitoring.factory.environment.model;

import lombok.Data;

@Data
public class FactoryStats {
	private Double currTemperature;
	private Double currHumidity;
	private Double currAirCondition;
	private Double currAirQuality;
	private Double targetTemperature;
	private Double targetHumidity;
	private Double targetAirCondition;
	private Double targetAirQuality;
}
