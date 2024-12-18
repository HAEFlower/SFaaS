package com.sfass.bsamonitoring.production.productionLine.service;

import java.util.List;

import com.sfass.bsamonitoring.production.productionLine.model.CurrentDailyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.CurrentMonthlyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.CurrentProductionLineProcessDetail;
import com.sfass.bsamonitoring.production.productionLine.model.CurrentProductionLineProcessResponse;
import com.sfass.bsamonitoring.production.productionLine.model.DateStatPk;
import com.sfass.bsamonitoring.production.productionLine.model.HourlyProcessStatsResponse;
import com.sfass.bsamonitoring.production.productionLine.model.NewTarget;
import com.sfass.bsamonitoring.production.productionLine.model.ProcessLog;
import com.sfass.bsamonitoring.production.productionLine.model.ProcessLogResponse;
import com.sfass.bsamonitoring.production.productionLine.model.ProductionLine;
import com.sfass.bsamonitoring.production.productionLine.model.ProductionLineUpdateResponse;
import com.sfass.bsamonitoring.production.productionLine.model.fault.HourWithFault;
import com.sfass.bsamonitoring.production.productionLine.model.fault.ProductionLineFault;

public interface ProductionLineService {
	ProductionLine getProductionLineById(Long id);
	List<ProductionLine> getProductionList();
	ProductionLineUpdateResponse updateProductionMonthlyTarget(Long id, Long newTarget);
	ProductionLineUpdateResponse updateProductionDailyTarget(Long id, Long newTarget);

	CurrentMonthlyProductionLineStats getCurrMonthlyStats(Long id);

	List<CurrentMonthlyProductionLineStats> getMonthlyStatsByYear(Long id, Integer year);

	CurrentDailyProductionLineStats getCurrDailyStats(Long id);

	List<CurrentDailyProductionLineStats> getDailyStatsByYearAndMonth(Long id, Integer year, Integer month);

	CurrentProductionLineProcessResponse getCurrentProcessStats(Long id);

	CurrentProductionLineProcessDetail updateProductionLineProcessBaseTime(Long productionLineProcessId, Long processId, NewTarget newTarget);

	HourlyProcessStatsResponse getTodayHourlyProcessStats(Long productionLIneId, Long processId);

	HourlyProcessStatsResponse getHourlyProcessStats(DateStatPk dateStatPk, Long productionLineId, Long processId);

	List<HourWithFault> getFaultStats();

	List<ProcessLogResponse> getProcessLog(Long productionId, Long processId);
}
