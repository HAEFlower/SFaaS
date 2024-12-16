package com.sfass.bsamonitoring.noti.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Noti {
	private Long notiId;
	private String contents;
	private Integer year;
	private Short month;
	private Short day;
	private String notiType;
	private LocalDateTime notiTime;
}
