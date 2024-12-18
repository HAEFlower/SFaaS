package com.sfass.bsamonitoring.production.productionLine.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.sfass.bsamonitoring.global.Util.MathUtil;
import com.sfass.bsamonitoring.production.productionLine.exception.ProductionLineNotFoundException;
import com.sfass.bsamonitoring.production.productionLine.mapper.ProductionLineMapper;
import com.sfass.bsamonitoring.production.productionLine.model.CurrentDailyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.CurrentMonthlyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.CurrentProductionLineProcessDetail;
import com.sfass.bsamonitoring.production.productionLine.model.CurrentProductionLineProcessResponse;
import com.sfass.bsamonitoring.production.productionLine.model.DailyProcessStats;
import com.sfass.bsamonitoring.production.productionLine.model.DailyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.DateStatPk;
import com.sfass.bsamonitoring.production.productionLine.model.HourlyProcessStats;
import com.sfass.bsamonitoring.production.productionLine.model.HourlyProcessStatsResponse;
import com.sfass.bsamonitoring.production.productionLine.model.HourlyProductionLineProcessDetail;
import com.sfass.bsamonitoring.production.productionLine.model.MonthlyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.NewTarget;
import com.sfass.bsamonitoring.production.productionLine.model.ProcessLog;
import com.sfass.bsamonitoring.production.productionLine.model.ProcessLogResponse;
import com.sfass.bsamonitoring.production.productionLine.model.ProductionLine;
import com.sfass.bsamonitoring.production.productionLine.model.ProductionLineProcessWithName;
import com.sfass.bsamonitoring.production.productionLine.model.ProductionLineUpdateResponse;
import com.sfass.bsamonitoring.production.productionLine.model.fault.HourWithFault;
import com.sfass.bsamonitoring.production.productionLine.model.fault.ProductionLineFault;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;

@Slf4j
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


	@Override
	public ProductionLineUpdateResponse updateProductionMonthlyTarget(Long id, Long newTarget) {
		LocalDateTime curr = LocalDateTime.now();
		Integer year = curr.getYear();
		Integer month = curr.getMonthValue();


		ProductionLine result = productionLineMapper.getProductionLineById(id);

		if (result == null) {
			throw new ProductionLineNotFoundException();
		}

		Map<String, Object> map = new HashMap<>();
		map.put("newTarget", newTarget);

		result.setMonthlyTarget(newTarget);
		productionLineMapper.updateMonthlyTarget(result);
		DateStatPk dateStatPk = new DateStatPk(id, year, month, 0);
		MonthlyProductionLineStats stats = productionLineMapper.getMonthlyStats(dateStatPk);
		map.put("dateStatPk", dateStatPk);
		productionLineMapper.updateMonthlyStatsTarget(map);

		return ProductionLineUpdateResponse.from(result, stats.getAccureProductionCnt());
	}

	@Override
	public ProductionLineUpdateResponse updateProductionDailyTarget(Long id, Long newTarget) {
		LocalDateTime curr = LocalDateTime.now();
		Integer year = curr.getYear();
		Integer month = curr.getMonthValue();
		Integer day = curr.getDayOfMonth();
//		day = 14;

		ProductionLine result = productionLineMapper.getProductionLineById(id);

		if (result == null) {
			throw new ProductionLineNotFoundException();
		}

		Map<String, Object> map = new HashMap<>();
		map.put("newTarget", newTarget);

		result.setDailyTarget(newTarget);
		DateStatPk dateStatPk = new DateStatPk(id, year, month, day);
		map.put("dateStatPk", dateStatPk);
		DailyProductionLineStats stats = productionLineMapper.getDailyStats(dateStatPk);
		productionLineMapper.updateDailyTarget(result);
		productionLineMapper.updateDailyStatsTarget(map);

		return ProductionLineUpdateResponse.from(result, stats.getAccureProductionCnt());
	}

	@Override
	public CurrentMonthlyProductionLineStats getCurrMonthlyStats(Long id) {
		LocalDateTime curr = LocalDateTime.now();
		Integer year = curr.getYear();
		Integer month = curr.getMonthValue();

		DateStatPk monthlyPk = new DateStatPk(id, year, month, null);

		MonthlyProductionLineStats stats = productionLineMapper.getMonthlyStats(monthlyPk);

		CurrentMonthlyProductionLineStats result =
			CurrentMonthlyProductionLineStats.from(stats);

		log.error(result.toString());

		return result;
	}

	@Override
	public List<CurrentMonthlyProductionLineStats> getMonthlyStatsByYear(Long id, Integer year) {
		DateStatPk monthlyPk = new DateStatPk(id, year, null, null);

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

		DateStatPk dailyPk = new DateStatPk(id, year, month, day);

		DailyProductionLineStats stats = productionLineMapper.getDailyStats(dailyPk);

		CurrentDailyProductionLineStats result =
			CurrentDailyProductionLineStats.from(stats);

		return result;
	}

	@Override
	public List<CurrentDailyProductionLineStats> getDailyStatsByYearAndMonth(Long id, Integer year, Integer month) {

		DateStatPk dailyPk = new DateStatPk(id, year, month, null);

		List<DailyProductionLineStats> stats = productionLineMapper.getDailyStatsByYearAndMonth(dailyPk);

		List<CurrentDailyProductionLineStats> result =
			stats.stream().map(
				CurrentDailyProductionLineStats::from
			).toList();

		return result;
	}

	@Override
	public CurrentProductionLineProcessResponse getCurrentProcessStats(Long id) {
		LocalDateTime curr = LocalDateTime.now();
		Integer year = curr.getYear();
		Integer month = curr.getMonthValue();
		Integer day = curr.getDayOfMonth();

		DateStatPk dailyPk = new DateStatPk(id, year, month, day);
		List<DailyProcessStats> dailyProcessStats = productionLineMapper.getDailyAllProcessStats(dailyPk);
		List<ProductionLineProcessWithName> processes = productionLineMapper.getProductionLineProcessWithName(id);

		List<CurrentProductionLineProcessDetail> details =
			dailyProcessStats.stream()
			.flatMap(stats -> processes.stream()
				.filter(process -> stats.getProductionLineProcessId().equals(process.getProductionLineProcessId()))
				.map(process -> CurrentProductionLineProcessDetail.from(stats, process))
			)
			.toList();

		final String productionLineName = processes.get(0).getProductionLineName();
		final Long productionLineId = processes.get(0).getProductionLineId();

		return new CurrentProductionLineProcessResponse(productionLineId, productionLineName, details);
	}

	@Override
	public CurrentProductionLineProcessDetail updateProductionLineProcessBaseTime(
		Long productionLineId,
		Long processId,
		NewTarget newTarget) {

		Long newValue = newTarget.newTarget();
		LocalDateTime curr = LocalDateTime.now();
		Integer year = curr.getYear();
		Integer month = curr.getMonthValue();
		Integer day = curr.getDayOfMonth();

		DateStatPk dailyPk = new DateStatPk(productionLineId, year, month, day);

		Map<String, Object> statMap = new HashMap<>();
		statMap.put("dailyPk", dailyPk);
		statMap.put("productionLineId", productionLineId);
		statMap.put("processId", processId);

		Map<String, Object> processMap = new HashMap<>();
		processMap.put("productionLineId", productionLineId);
		processMap.put("processId", processId);
		processMap.put("newValue", newValue);

		DailyProcessStats stats = productionLineMapper.getDailyProcessStatsById(statMap);
		productionLineMapper.updateProductionLineProcessBaseTime(processMap);
		ProductionLineProcessWithName process = productionLineMapper.getProductionLineProcessWithNameOneByMap(processMap);
		return CurrentProductionLineProcessDetail.from(stats, process);
	}

	@Override
	public HourlyProcessStatsResponse getTodayHourlyProcessStats(Long productionLineId, Long processId) {
		LocalDateTime curr = LocalDateTime.now();
		Integer year = curr.getYear();
		Integer month = curr.getMonthValue();
		// TODO: 수정
		// Integer day = curr.getDayOfMonth();
		Integer day = 14;

		DateStatPk dailyPk = new DateStatPk(0L, year, month, day);
		Map<String, Object> map = new HashMap<>();
		map.put("dailyPk", dailyPk);
		map.put("productionLineId", productionLineId);
		map.put("processId", processId);

		List<HourlyProcessStats> hourlyProcessStatsList = productionLineMapper.getHourlyProcessStats(map);
		ProductionLineProcessWithName process = productionLineMapper.getProductionLineProcessWithNameOneByMap(map);

		List<HourlyProductionLineProcessDetail> details =
			hourlyProcessStatsList.stream()
				.map((HourlyProcessStats stats) -> HourlyProductionLineProcessDetail.from(stats, process))
				.toList();
		return HourlyProcessStatsResponse.from(process, details);
	}

	@Override
	public HourlyProcessStatsResponse getHourlyProcessStats(
		DateStatPk dateStatPk,
		Long productionLineId,
		Long processId
		) {
		Integer year = dateStatPk.year();
		Integer month = dateStatPk.month();
		Integer day = dateStatPk.day();

		DateStatPk dailyPk = new DateStatPk(dateStatPk.id(), year, month, day);
		Map<String, Object> map = new HashMap<>();
		map.put("dailyPk", dailyPk);
		map.put("productionLineId", productionLineId);
		map.put("processId", processId);

		List<HourlyProcessStats> hourlyProcessStatsList = productionLineMapper.getHourlyProcessStats(map);
		ProductionLineProcessWithName process = productionLineMapper.getProductionLineProcessWithNameOneByMap(map);

		List<HourlyProductionLineProcessDetail> details =
			hourlyProcessStatsList.stream()
				.map((HourlyProcessStats stats) -> HourlyProductionLineProcessDetail.from(stats, process))
				.toList();
		return HourlyProcessStatsResponse.from(process, details);
	}

	@Override
	public List<HourWithFault> getFaultStats() {
		List<ProductionLineFault> list = productionLineMapper.getProductionLIneFault();

		list.forEach(data -> {
			double faultRate = ((double) data.getFaultCnt() / data.getTotalCnt()) * 100;
			data.setFaultRate(MathUtil.roundToTwoDecimalPlaces(faultRate));
		});

		Map<Integer, HourWithFault> map = new HashMap<>();
		for (int i = 0; i < 24; ++i) {
			map.put(i, new HourWithFault(i));
		}

		List<HourWithFault> result = new ArrayList<>();

		list.forEach(data -> {
			map.get(data.getHour()).addData(data);
		});

		for (int i = 0; i < 24; i++) {
			result.add(map.get(i));
		}

		return result;
	}

	@Override
	public List<ProcessLogResponse> getProcessLog(Long productionId, Long processId) {
		Map<String, Long> map = new HashMap<>();
		map.put("productionLineId", productionId);
		map.put("processId", processId);
		List<ProcessLog> list = productionLineMapper.getProcessLog(map);

		List<ProcessLogResponse> result =
			list.stream()
				.map(ProcessLogResponse::from)
				.toList();
		return result;
	}

}
