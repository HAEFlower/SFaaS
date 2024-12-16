package com.sfass.bsamonitoring.production.productionLine.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentDailyProductionLineStats {
	private Long ProductionLineId;
	private Integer year;
	private Short month;
	private Short day;
	private Long totalTime; // 분단위
	private Long currTime;
	private Long TotalCnt;
	private Long currCnt;

	public static CurrentDailyProductionLineStats from(DailyProductionLineStats stats) {
		return new CurrentDailyProductionLineStats(
			stats.getProductionLineId(),
			stats.getYear(),
			stats.getMonth(),
			stats.getDay(),
			stats.getDailyExecTime(), // 분단위
			stats.getAccureProductionTime(), // 분단위
			stats.getDailyTarget(),
			stats.getAccureProductionCnt()
		);
	}
}
