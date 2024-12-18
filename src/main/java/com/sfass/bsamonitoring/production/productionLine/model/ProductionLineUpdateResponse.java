package com.sfass.bsamonitoring.production.productionLine.model;

import java.time.LocalDateTime;

import com.sfass.bsamonitoring.global.common.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductionLineUpdateResponse {

	private Long productionLineId;
	private Long dailyTarget;
	private Long monthlyTarget;
	private Long currentCnt;
	private LocalDateTime productionStartTime;
	private LocalDateTime productionEndTime;
	private StatusEnum status;
	private String product;

	public static ProductionLineUpdateResponse from(ProductionLine productionLine, Long currentCnt) {
		return new ProductionLineUpdateResponse(
			productionLine.getProductionLineId(),
			productionLine.getDailyTarget(),
			productionLine.getMonthlyTarget(),
			currentCnt,
			productionLine.getProductionStartTime(),
			productionLine.getProductionEndTime(),
			productionLine.getStatus(),
			productionLine.getProduct()
		);
	}
}
