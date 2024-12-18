package com.sfass.bsamonitoring.production.productionLine.model.productionLineProcess;

import com.sfass.bsamonitoring.global.Util.MathUtil;
import com.sfass.bsamonitoring.global.common.LevelEnum;
import com.sfass.bsamonitoring.production.productionLine.model.ProductionLineProcessWithName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HourlyProductionLineProcessDetail {
	private Integer hour;
	private Integer baseExecTime; // sec
	private Double avgTime;
	private LevelEnum avgLevel;
	private Long totalCnt;
	private Long faultCnt;
	private Double faultRate;

	public static HourlyProductionLineProcessDetail from(HourlyProcessStats stats, ProductionLineProcessWithName process) {

		LevelEnum avgLevel = getTimeLevel(stats.getAvgTime(), process.getBaseExecTime());
		Double faultRate = MathUtil.roundToTwoDecimalPlaces((stats.getFaultCnt() / (double)stats.getTotalCnt()) * 100);

		return new HourlyProductionLineProcessDetail(
			stats.getHour(),
			process.getBaseExecTime(),
			MathUtil.roundToTwoDecimalPlaces(stats.getAvgTime()),
			avgLevel,
			stats.getTotalCnt(),
			stats.getFaultCnt(),
			faultRate
		);
	}

	private static LevelEnum getTimeLevel(Double avgTime, Integer baseTime) {
		if (baseTime >= avgTime) {
			return LevelEnum.GREEN;
		} else if (avgTime - baseTime <= 2) {
			return LevelEnum.YELLOW;
		} else if (avgTime - baseTime <= 3) {
			return LevelEnum.RED;
		}

		return LevelEnum.GREEN;
	}
}
