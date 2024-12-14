package com.sfass.bsamonitoring.production.productionLine.exception;

import com.sfass.bsamonitoring.global.error.exception.CustomException;
import com.sfass.bsamonitoring.global.error.exception.ErrorCode;

public class ProductionLineNotFoundException extends CustomException {
	public ProductionLineNotFoundException() {
		super(ErrorCode.PRODUCTION_LINE_NOT_FOUND);
	}
}
