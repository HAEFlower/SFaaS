package com.sfass.bsamonitoring.production.productionLine.model;

public record DateStatPk(
	Long id,
	Integer year,
	Integer month,
	Integer day
) {
}
