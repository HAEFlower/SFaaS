package com.sfass.bsamonitoring.production.productionLine.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sfass.bsamonitoring.production.productionLine.model.DailyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.ProductionLIneStatPk;
import com.sfass.bsamonitoring.production.productionLine.model.MonthlyProductionLineStats;
import com.sfass.bsamonitoring.production.productionLine.model.ProductionLine;

@Mapper
public interface ProductionLineMapper {
	ProductionLine getProductionLineById(Long id);

	List<ProductionLine> getAllProduction();

	void updateMonthlyTarget(ProductionLine result);

	void updateDailyTarget(ProductionLine result);

	MonthlyProductionLineStats getMonthlyStats(ProductionLIneStatPk monthlyPk);

	List<MonthlyProductionLineStats> getMonthlyStatsByYear(ProductionLIneStatPk monthlyPk);

	DailyProductionLineStats getDailyStats(ProductionLIneStatPk monthlyPk);

	List<DailyProductionLineStats> getDailyStatsByYearAndMonth(ProductionLIneStatPk dailyPk);
}
