package com.myx4play.hello_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myx4play.hello_service.model.Hello;

@RestController
public class HelloServiceController {

	@GetMapping(value = "/hello")
	public Hello hello(@RequestParam(value = "firstName", defaultValue = "world") String firstName,
			@RequestParam(value = "lastName", defaultValue = "world") String lastName) {
		Hello response = new Hello("Hello", firstName);
		response.setFullName();

		return response;
	}

	@PostMapping(value = "/hello2")
	public Hello hello2(@RequestBody Hello request) {

		Hello response = request;
		response.setFullName();

		return response;
	}

	@GetMapping(value = "/hello-get")
	public Hello helloGet(@RequestBody Hello request) {

		Hello response = request;
		response.setFullName();

		return response;
	}
}
