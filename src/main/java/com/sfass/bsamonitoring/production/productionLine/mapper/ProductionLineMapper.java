package com.sfass.bsamonitoring.production.productionLine.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sfass.bsamonitoring.production.productionLine.model.DailyProcessStats;
import com.sfass.bsamonitoring.production.productionLine.model.DailyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.DateStatPk;
import com.sfass.bsamonitoring.production.productionLine.model.HourlyProcessStats;
import com.sfass.bsamonitoring.production.productionLine.model.MonthlyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.ProductionLine;
import com.sfass.bsamonitoring.production.productionLine.model.ProductionLineProcessWithName;
import com.sfass.bsamonitoring.production.productionLine.model.fault.ProductionLineFault;

@Mapper
public interface ProductionLineMapper {
	ProductionLine getProductionLineById(Long id);

	List<ProductionLine> getAllProduction();

	void updateMonthlyTarget(ProductionLine result);

	void updateDailyTarget(ProductionLine result);

	MonthlyProductionLineStats getMonthlyStats(DateStatPk monthlyPk);

	List<MonthlyProductionLineStats> getMonthlyStatsByYear(DateStatPk monthlyPk);

	DailyProductionLineStats getDailyStats(DateStatPk monthlyPk);

	List<DailyProductionLineStats> getDailyStatsByYearAndMonth(DateStatPk dailyPk);

	List<DailyProcessStats> getDailyAllProcessStats(DateStatPk id);

	List<ProductionLineProcessWithName> getProductionLineProcessWithName(Long id);

	DailyProcessStats getDailyProcessStatsById(Map<String, Object> statMap);

	void updateProductionLineProcessBaseTime(Map<String, Object> processMap);

	ProductionLineProcessWithName getProductionLineProcessWithNameOne(Long id);

	List<HourlyProcessStats> getHourlyProcessStats(DateStatPk dailyPk);

	List<ProductionLineFault> getProductionLIneFault();
}
