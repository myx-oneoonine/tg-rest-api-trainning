package com.java;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloProjectController {

	@RequestMapping("/hello")
	public String hello() {
		return "Hello world";
	}
}
