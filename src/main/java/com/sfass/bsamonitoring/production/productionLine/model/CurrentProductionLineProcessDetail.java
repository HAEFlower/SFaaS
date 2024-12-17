package com.sfass.bsamonitoring.production.productionLine.model;

import com.sfass.bsamonitoring.global.Util.PercentageGeneratorUtil;
import com.sfass.bsamonitoring.global.common.LevelEnum;
import com.sfass.bsamonitoring.global.common.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentProductionLineProcessDetail {
	private Long productionLineProcessId;
	private Long productionLineId;
	private Long processId;
	private String productionLineName;
	private String processName;
	private Integer baseExecTime; // sec
	private Long avgTime;
	private String faultRate;
	private LevelEnum avgLevel;
	private Integer lastExecTime;
	private LevelEnum lastExecTimeLevel;
	private StatusEnum status;

	public static CurrentProductionLineProcessDetail from(DailyProcessStats stats, ProductionLineProcessWithName process) {

		Long avgTime = stats.getAccureProductionTime() / stats.getAccureProductionCnt();
		LevelEnum avgLevel = getTimeLevel(avgTime, process.getBaseExecTime());
		LevelEnum lastLevel = getTimeLevel((long)process.getLastExecTime(), process.getBaseExecTime());

		return new CurrentProductionLineProcessDetail(
			stats.getProductionLineProcessId(),
			stats.getProductionLineId(),
			stats.getProcessId(),
			process.getProductionLineName(),
			process.getProcessName(),
			process.getBaseExecTime(),
			avgTime,
			PercentageGeneratorUtil.formatPercentage(PercentageGeneratorUtil.generatePercentage()),
			avgLevel,
			process.getLastExecTime(),
			lastLevel,
			process.getStatus()
		);
	}

	private static LevelEnum getTimeLevel(Long avgTime, Integer baseTime) {

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
