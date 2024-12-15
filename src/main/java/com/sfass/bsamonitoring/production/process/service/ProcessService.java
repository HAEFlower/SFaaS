package com.sfass.bsamonitoring.production.process.service;

import java.util.List;

import com.sfass.bsamonitoring.production.process.model.Process;

public interface ProcessService {
	Process getProcessById(Long id);

	List<Process> getAllProcess();
}
