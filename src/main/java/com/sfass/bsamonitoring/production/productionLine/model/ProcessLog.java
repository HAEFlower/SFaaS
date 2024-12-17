package com.sfass.bsamonitoring.production.productionLine.model;

import lombok.Data;

@Data
public class ProcessLog {
	private Long processId;
	private Integer year;
	private Short month;
	private Short day;
	private Short hour;
	private Short minute;
	private Boolean fault;
	private String contents;
	private Long execTime;
}
