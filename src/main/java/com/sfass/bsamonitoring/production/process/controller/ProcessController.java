package com.sfass.bsamonitoring.production.process.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sfass.bsamonitoring.production.process.model.Process;
import com.sfass.bsamonitoring.production.process.service.ProcessService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/process")
public class ProcessController {

	private final ProcessService processService;

	@GetMapping()
	public List<Process> getAllProcess() {
		return processService.getAllProcess();
	}

	@GetMapping("/{id}")
	public Process getProcessById(@PathVariable Long id) {
		return processService.getProcessById(id);
	}

}
