package com.sfass.bsamonitoring.factory.environment.service;

import com.sfass.bsamonitoring.factory.environment.model.DoubleNewTarget;
import com.sfass.bsamonitoring.factory.environment.model.FactoryEnvResponse;
import com.sfass.bsamonitoring.factory.environment.model.FactoryStats;

public interface FactoryEnvService {
	FactoryEnvResponse getCurrentEnv();

	FactoryEnvResponse updateTargetTemperature(DoubleNewTarget newTarget);
}
