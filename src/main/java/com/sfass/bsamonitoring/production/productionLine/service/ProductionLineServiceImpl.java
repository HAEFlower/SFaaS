package com.sfass.bsamonitoring.production.productionLine.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sfass.bsamonitoring.production.productionLine.exception.ProductionLineNotFoundException;
import com.sfass.bsamonitoring.production.productionLine.mapper.ProductionLineMapper;
import com.sfass.bsamonitoring.production.productionLine.model.CurrentDailyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.CurrentMonthlyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.DailyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.ProductionLIneStatPk;
import com.sfass.bsamonitoring.production.productionLine.model.MonthlyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.ProductionLine;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductionLineServiceImpl implements ProductionLineService {

	private final ProductionLineMapper productionLineMapper;

	@Override
	public ProductionLine getProductionLineById(Long id) throws ProductionLineNotFoundException {

		ProductionLine result =  productionLineMapper.getProductionLineById(id);

		if (result == null) {
			throw new ProductionLineNotFoundException();
		}

		return result;
	}

	@Override
	public List<ProductionLine> getProductionList() {

		List<ProductionLine> result = productionLineMapper.getAllProduction();

		if (result == null || result.isEmpty()) {
			throw new ProductionLineNotFoundException();
		}

		return result;
	}

	// TODO: 현재 수량도 같이 전송하는 고도화 고려
	@Override
	public ProductionLine updateProductionMonthlyTarget(Long id, Long newTarget) {
		ProductionLine result = productionLineMapper.getProductionLineById(id);

		if (result == null) {
			throw new ProductionLineNotFoundException();
		}

		result.setMonthlyTarget(newTarget);
		productionLineMapper.updateMonthlyTarget(result);
		return result;
	}

	// TODO: 현재 수량도 같이 전송하는 고도화 고려
	@Override
	public ProductionLine updateProductionDailyTarget(Long id, Long newTarget) {
		ProductionLine result = productionLineMapper.getProductionLineById(id);

		if (result == null) {
			throw new ProductionLineNotFoundException();
		}

		result.setDailyTarget(newTarget);
		productionLineMapper.updateDailyTarget(result);
		return result;
	}

	@Override
	public CurrentMonthlyProductionLineStats getCurrMonthlyStats(Long id) {
		LocalDateTime curr = LocalDateTime.now();
		Integer year = curr.getYear();
		Integer month = curr.getMonthValue();

		ProductionLIneStatPk monthlyPk = new ProductionLIneStatPk(id, year, month, null);

		MonthlyProductionLineStats stats = productionLineMapper.getMonthlyStats(monthlyPk);

		CurrentMonthlyProductionLineStats result =
			CurrentMonthlyProductionLineStats.from(stats);

		return result;
	}

	@Override
	public List<CurrentMonthlyProductionLineStats> getMonthlyStatsByYear(Long id, Integer year) {
		ProductionLIneStatPk monthlyPk = new ProductionLIneStatPk(id, year, null, null);

		List<MonthlyProductionLineStats> stats = productionLineMapper.getMonthlyStatsByYear(monthlyPk);

		List<CurrentMonthlyProductionLineStats> result =
			stats.stream().map(
				CurrentMonthlyProductionLineStats::from
			).toList();

		return result;
	}

	@Override
	public CurrentDailyProductionLineStats getCurrDailyStats(Long id) {
		LocalDateTime curr = LocalDateTime.now();
		Integer year = curr.getYear();
		Integer month = curr.getMonthValue();
		Integer day = curr.getDayOfMonth();

		ProductionLIneStatPk dailyPk = new ProductionLIneStatPk(id, year, month, day);

		DailyProductionLineStats stats = productionLineMapper.getDailyStats(dailyPk);

		CurrentDailyProductionLineStats result =
			CurrentDailyProductionLineStats.from(stats);

		return result;
	}

	@Override
	public List<CurrentDailyProductionLineStats> getDailyStatsByYearAndMonth(Long id, Integer year, Integer month) {

		ProductionLIneStatPk dailyPk = new ProductionLIneStatPk(id, year, month, null);

		List<DailyProductionLineStats> stats = productionLineMapper.getDailyStatsByYearAndMonth(dailyPk);

		List<CurrentDailyProductionLineStats> result =
			stats.stream().map(
				CurrentDailyProductionLineStats::from
			).toList();

		return result;
	}

}
