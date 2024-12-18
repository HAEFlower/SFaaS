package com.sfass.bsamonitoring.noti.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sfass.bsamonitoring.noti.model.Noti;

@Mapper
public interface NotiMapper {
	List<Noti> getAllNoti();
}
