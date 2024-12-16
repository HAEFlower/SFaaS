package com.sfass.bsamonitoring.production.productionLine.model;

import com.sfass.bsamonitoring.global.Util.DateTimeUtil;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentMonthlyProductionLineStats {
	private Long ProductionLineId;
	private Integer year;
	private Short month;
	private Long totalTime; // 분단위
	private Long currTime;
	private Long TotalCnt;
	private Long currCnt;

	public static CurrentMonthlyProductionLineStats from(MonthlyProductionLineStats stats) {
		return new CurrentMonthlyProductionLineStats(
			stats.getProductionLineId(),
			stats.getYear(),
			stats.getMonth(),
			stats.getMonthlyExecTime(),
			stats.getAccureProductionTime(),
			stats.getMonthlyProductionTarget(),
			stats.getAccureProductionCnt()
		);
	}
}
