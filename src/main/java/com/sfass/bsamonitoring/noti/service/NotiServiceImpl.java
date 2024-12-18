package com.sfass.bsamonitoring.noti.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sfass.bsamonitoring.noti.mapper.NotiMapper;
import com.sfass.bsamonitoring.noti.model.Noti;
import com.sfass.bsamonitoring.noti.model.NotiResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotiServiceImpl implements NotiService {

	private final NotiMapper notiMapper;

	@Override
	public List<NotiResponse> getNoti() {
		List<Noti> list = notiMapper.getAllNoti();
		List<NotiResponse> result =
			list.stream()
				.map(NotiResponse::from)
				.toList();
		return result;
	}
}
