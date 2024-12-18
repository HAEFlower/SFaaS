package com.sfass.bsamonitoring.noti.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sfass.bsamonitoring.noti.model.NotiResponse;
import com.sfass.bsamonitoring.noti.service.NotiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/noti")
@RequiredArgsConstructor
public class NotiController {

	private final NotiService notiService;

	@GetMapping
	public List<NotiResponse> getNoti() {
		return notiService.getNoti();
	}
}
