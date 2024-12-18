package com.sfass.bsamonitoring.production.productionLine.model.productionLineProcess;

import java.util.List;

import com.sfass.bsamonitoring.production.productionLine.model.ProductionLineProcessWithName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HourlyProcessStatsResponse {
	private Long productionLineProcessId;
	private Long productionLineId;
	private Long processId;
	private String productionName;
	private String processName;
	private List<HourlyProductionLineProcessDetail> stats;

	public static HourlyProcessStatsResponse from(
		ProductionLineProcessWithName process,
		List<HourlyProductionLineProcessDetail> stats
	) {
		return new HourlyProcessStatsResponse(
			process.getProductionLineProcessId(),
			process.getProductionLineId(),
			process.getProcessId(),
			process.getProductionLineName(),
			process.getProcessName(),
			stats
		);
	}
}
