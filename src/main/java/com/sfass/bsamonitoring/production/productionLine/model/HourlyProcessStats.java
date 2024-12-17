package com.sfass.bsamonitoring.production.productionLine.model;

import lombok.Data;

@Data
public class HourlyProcessStats {
	private Integer hour;
	private Double avgTime;
	private Long totalCnt;
	private Long faultCnt;
}