package com.sfass.bsamonitoring.production.productionLine.model;

import lombok.Data;

@Data
public class MonthlyProductionLineStats {
	private Long ProductionLineId;
	private Integer year;
	private Short month;
	private Long accureProductionTime; // 분단위
	private Long accureProductionCnt;
	private Long monthlyProductionTarget; // 수량 목표
	private Long monthlyExecTime;
}
