package com.sfass.bsamonitoring.production.productionLine.model.fault;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class HourWithFault {
	private Integer hour;
	private List<ProductionLineFault> lines;

	public HourWithFault(Integer hour) {
		this.hour = hour;
		lines = new ArrayList<>();
	}

	public void addData(ProductionLineFault data) {
		lines.add(data);
	}
}
