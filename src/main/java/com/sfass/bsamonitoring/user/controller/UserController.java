package com.sfass.bsamonitoring.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sfass.bsamonitoring.user.model.User;
import com.sfass.bsamonitoring.user.model.UserLoginDto;
import com.sfass.bsamonitoring.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@PostMapping("/login")
	public User loginUser(@RequestBody UserLoginDto userLoginDto) {

		User user = userService.loginUser(userLoginDto);

		return user;
	}

	@GetMapping()
	public List<User> getUsers(
		@RequestParam(required = false) String userName,
		@RequestParam(required = false) String productionLine,
		@RequestParam(required = false) String processLine
	) {
		Map<String, String> map = new HashMap<>();
		map.put("userName", userName);
		map.put("productionLine", productionLine);
		map.put("processLine", processLine);

		List<User> users = userService.getUsers(map);

		return users;
	}
}
