package com.sfass.bsamonitoring.production.productionLine.model.productionLineProcess;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentProductionLineProcessResponse {
	private Long productionLineId;
	private String productionLineName;
	private List<CurrentProductionLineProcessDetail> processes;
}
