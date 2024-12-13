package com.sfass.bsamonitoring.user.model;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;


/*
	`user_id`	BIGINT	NOT NULL,
	`user_pwd`	VARCHAR(20)	NOT NULL,
	`user_name`	VARCHAR(20)	NOT NULL,
	`email`	VARCHAR(50)	NULL,
	`last_login_time`	DATETIME	NULL,
	`login_status`	BOOLEAN	NOT NULL,
	`department`	VARCHAR(20)	NOT NULL,
	`position`	VARCHAR(20)	NOT NULL,
	`emp_no`	VARCHAR(20)	NOT NULL,
	`production_line`	VARCHAR(20)	NOT NULL,
	`process_line`	VARCHAR(20)	NOT NULL
 */

@Data
public class User {
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private LocalDateTime lastLoginTime;
	private boolean loginStatus;
	private String department;
	private String position;
	private String empNo;
	private String productionLine;
	private String processLine;
	private String auth;

	public boolean getLoginStatus() {
		return this.loginStatus;
	}
}


