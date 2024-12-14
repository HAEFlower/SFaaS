package com.sfass.bsamonitoring.production.productionLine.model;

import java.time.LocalDateTime;

import com.sfass.bsamonitoring.global.common.StatusEnum;

import lombok.Data;

@Data
public class ProductionLine {
	private Long productionLineId;
	private Long dailyTarget;
	private Long monthlyTarget;
	private LocalDateTime productionStartTime;
	private LocalDateTime productionEndTime;
	private StatusEnum status;
	private String product;

	public void startLine() {
		this.productionStartTime = LocalDateTime.now();
	}

	public void endLine() {
		this.productionEndTime = LocalDateTime.now();
	}
}
