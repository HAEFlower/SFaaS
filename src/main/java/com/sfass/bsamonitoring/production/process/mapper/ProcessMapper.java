package com.sfass.bsamonitoring.production.process.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sfass.bsamonitoring.production.process.model.Process;

@Mapper
public interface ProcessMapper {
	Process getProcessById(Long id);

	List<Process> getAllProcess();
}
