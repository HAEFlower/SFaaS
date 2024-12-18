package com.sfass.bsamonitoring.factory.environment.service;

import org.springframework.stereotype.Service;

import com.sfass.bsamonitoring.factory.environment.mapper.FactoryEnvMapper;
import com.sfass.bsamonitoring.factory.environment.model.DoubleNewTarget;
import com.sfass.bsamonitoring.factory.environment.model.FactoryEnvResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FactoryEnvServiceImpl implements FactoryEnvService {

	private final FactoryEnvMapper factoryEnvMapper;

	@Override
	public FactoryEnvResponse getCurrentEnv() {
		return FactoryEnvResponse.from(factoryEnvMapper.getCurrentEnv());
	}

	@Override
	public FactoryEnvResponse updateTargetTemperature(DoubleNewTarget newTarget) {
		factoryEnvMapper.updateTargetTemperature(newTarget.newTarget());
		return FactoryEnvResponse.from(factoryEnvMapper.getCurrentEnv());
	}

	@Override
	public FactoryEnvResponse updateTargetAirCondition(DoubleNewTarget newTarget) {
		factoryEnvMapper.updateTargetAirCondition(newTarget.newTarget());
		return FactoryEnvResponse.from(factoryEnvMapper.getCurrentEnv());
	}
}
