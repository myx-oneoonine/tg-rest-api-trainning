package com.demo.myx.security.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.myx.security.model.User;
import com.demo.myx.security.model.UserRole;
import com.demo.myx.security.service.SignupService;

@RestController
public class SignupController {

	@Autowired
	private SignupService signupService;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {

		user.setRoles(Arrays.asList(new UserRole("USER")));
		signupService.addUser(user);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
