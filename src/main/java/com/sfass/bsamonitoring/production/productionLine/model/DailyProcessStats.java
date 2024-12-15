package com.sfass.bsamonitoring.production.productionLine.model;

import lombok.Data;

@Data
public class DailyProcessStats {
	private Long productionLineProcessId;
	private Long productionLineId;
	private Long processId;
	private Integer year;
	private Short month;
	private Short day;
	private Long accureProductionTime;
	private Long accureProductionCnt;
}
