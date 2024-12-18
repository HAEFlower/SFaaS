package com.sfass.bsamonitoring.factory.environment.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sfass.bsamonitoring.factory.environment.model.FactoryStats;

@Mapper
public interface FactoryEnvMapper {
	FactoryStats getCurrentEnv();

	void updateTargetTemperature(Double newTarget);

	void updateTargetAirCondition(Double aDouble);
}
