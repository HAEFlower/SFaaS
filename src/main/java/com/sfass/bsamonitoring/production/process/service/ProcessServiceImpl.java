package com.sfass.bsamonitoring.production.process.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sfass.bsamonitoring.production.process.exception.ProcessNotFoundException;
import com.sfass.bsamonitoring.production.process.mapper.ProcessMapper;
import com.sfass.bsamonitoring.production.process.model.Process;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {

	private final ProcessMapper processMapper;

	@Override
	public Process getProcessById(Long id) {
		Process result = processMapper.getProcessById(id);

		if (result == null) {
			throw new ProcessNotFoundException();
		}

		return result;
	}

	@Override
	public List<Process> getAllProcess() {
		List<Process> result = processMapper.getAllProcess();

		return result;
	}
}
