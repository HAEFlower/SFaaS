package com.sfass.bsamonitoring.factory.environment.model;

import lombok.Data;

@Data
public class FactoryEnv {
	private Long environmentId;
	private Double temperature;
	private Double humidity;
	private Double airCondition;
	private Double airQuality;
}
