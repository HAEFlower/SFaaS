package com.sfass.bsamonitoring.production.productionLine.model;

import com.sfass.bsamonitoring.global.common.StatusEnum;

import lombok.Data;

@Data
public class ProductionLineProcessWithName {
	private Long productionLineProcessId;
	private Long productionLineId;
	private Long processId;
	private String productionLineName;
	private String processName;
	private Integer baseExecTime; // sec
	private Integer lastExecTime;
	private StatusEnum status;
}
