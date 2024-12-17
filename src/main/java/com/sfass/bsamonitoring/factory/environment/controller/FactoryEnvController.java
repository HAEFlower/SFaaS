package com.sfass.bsamonitoring.factory.environment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sfass.bsamonitoring.factory.environment.model.DoubleNewTarget;
import com.sfass.bsamonitoring.factory.environment.model.FactoryEnvResponse;
import com.sfass.bsamonitoring.factory.environment.service.FactoryEnvService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/factory/env")
public class FactoryEnvController {

	private final FactoryEnvService factoryEnvService;

	@GetMapping
	public FactoryEnvResponse getCurrentEnv() {
		return factoryEnvService.getCurrentEnv();
	}

	@PostMapping("/temperature")
	public FactoryEnvResponse updateTargetTemperature (@RequestBody DoubleNewTarget newTarget) {
		return factoryEnvService.updateTargetTemperature(newTarget);
	}

	@PostMapping("/air-condition")
	public FactoryEnvResponse updateTargetAirCondition (@RequestBody DoubleNewTarget newTarget) {
		return factoryEnvService.updateTargetAirCondition(newTarget);
	}
}
