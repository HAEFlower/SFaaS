package com.sfass.bsamonitoring.production.productionLine.model;

import jakarta.persistence.criteria.CriteriaBuilder;

public record ProductionLIneStatPk(
	Long id,
	Integer year,
	Integer month,
	Integer day
) {
}
