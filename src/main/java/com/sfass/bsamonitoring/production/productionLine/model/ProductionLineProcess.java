package com.sfass.bsamonitoring.production.productionLine.model;

import com.sfass.bsamonitoring.global.common.StatusEnum;

import lombok.Data;

@Data
public class ProductionLineProcess {
	private Long productionLineProcessId;
	private Long productionLineId;
	private Long processId;
	private Integer baseExecTime; // sec
	private StatusEnum status;

	public void updateBaseExecTime(Integer newTime) {
		if (newTime < 0) {
			throw new IllegalArgumentException("정수만 가능합니다.");
		}

		this.baseExecTime = newTime;
	}

	public void updateStatus(StatusEnum newStatus) {
		this.status = newStatus;
	}
}
