package com.sfass.bsamonitoring.production.productionLine.model.fault;

import lombok.Data;

@Data
public class ProductionLineFault {
	private Long productionLineId;
	private String productName;
	private Long totalCnt;
	private Long faultCnt;
	private Integer hour;
	private Double faultRate;
}
