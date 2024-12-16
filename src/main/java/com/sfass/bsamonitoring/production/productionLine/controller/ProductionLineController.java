package com.sfass.bsamonitoring.production.productionLine.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sfass.bsamonitoring.production.productionLine.model.CurrentDailyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.CurrentMonthlyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.NewTarget;
import com.sfass.bsamonitoring.production.productionLine.model.ProductionLine;
import com.sfass.bsamonitoring.production.productionLine.service.ProductionLineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produce-lines")
@RequiredArgsConstructor
public class ProductionLineController {

	private final ProductionLineService productionLineService;

	@GetMapping("/{id}")
	public ProductionLine getProductionLIneById(@PathVariable Long id) {
		return productionLineService.getProductionLineById(id);
	}

	@GetMapping
	public List<ProductionLine> getProductionLines() {
		return productionLineService.getProductionList();
	}

	@PostMapping("/monthly/{id}")
	public ProductionLine updateMontlyTarget(@PathVariable Long id, @RequestBody NewTarget newTarget) {
		return productionLineService.updateProductionMonthlyTarget(id, newTarget.newTarget());
	}

	@PostMapping("/daily/{id}")
	public ProductionLine updateDailyTarget(@PathVariable Long id, @RequestBody NewTarget newTarget) {
		return productionLineService.updateProductionDailyTarget(id, newTarget.newTarget());
	}

	@GetMapping("monthly/{id}")
	public List<CurrentMonthlyProductionLineStats> getCurrMonthlyStats(
		@PathVariable Long id,
		@RequestParam(value = "year", required = false) Integer year
	) {
		if (year == null) {
			return Collections.singletonList(productionLineService.getCurrMonthlyStats(id));
		}

		return productionLineService.getMonthlyStatsByYear(id, year);
	}

	@GetMapping("daily/{id}")
	public List<CurrentDailyProductionLineStats> getCurrDailyStats(
		@PathVariable Long id,
		@RequestParam(value = "year", required = false) Integer year,
		@RequestParam(value = "month", required = false) Integer month
	) {
		if (year == null || month == null) {
			return Collections.singletonList(productionLineService.getCurrDailyStats(id));
		}

		return productionLineService.getDailyStatsByYearAndMonth(id, year, month);
	}

}
