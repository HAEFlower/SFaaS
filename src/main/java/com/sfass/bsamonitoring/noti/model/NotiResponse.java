package com.sfass.bsamonitoring.noti.model;

import java.time.LocalDateTime;

import com.sfass.bsamonitoring.global.Util.DateTimeUtil;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NotiResponse {
	private Long notiId;
	private String contents;
	private String notiType;
	private String time;

	public static NotiResponse from (Noti noti) {
		return new NotiResponse(
			noti.getNotiId(),
			noti.getContents(),
			noti.getNotiType(),
			DateTimeUtil.makeDateFormat(noti.getNotiTime())
		);
	}

}
