package com.sfass.bsamonitoring.production.productionLine.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProcessLogResponse {
	private Long processId;
	private String contents;
	private Boolean fault;
	private String time;

	public static ProcessLogResponse from(ProcessLog log) {
		return new ProcessLogResponse(
			log.getProcessId(),
			makeContents(log),
			log.getFault(),
			makeTime(log)
		);
	}

	private static String makeContents(ProcessLog log) {
		StringBuilder builder = new StringBuilder();

		builder.append(log.getProcessId()).append("번 공정: ")
			.append(log.getContents());


		return builder.toString();
	}

	private static String makeTime(ProcessLog log) {
		StringBuilder builder = new StringBuilder();

		builder.append("[ ")
			.append(String.format("%04d-%02d-%02d %02d:%02d",
				log.getYear(),
				log.getMonth(),
				log.getDay(),
				log.getHour(),
				log.getMinute()))
			.append(" ]");


		return builder.toString();
	}
}
