package com.sfass.bsamonitoring.production.productionLine.model;

import lombok.Data;

@Data
public class DailyProductionLineStats {
	private Long ProductionLineId;
	private Integer year;
	private Short month;
	private Short day;
	private Long accureProductionTime; // 분단위
	private Long accureProductionCnt;
	private Long dailyTarget; // 수량 목표
	private Long dailyExecTime;
}
