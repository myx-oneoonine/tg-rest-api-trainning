package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.SoapService;
import com.example.demo.service.TempRequest;
import com.example.demo.service.TempResponse;


@RestController
@RequestMapping("/soap")
public class DemoController {

	@Autowired
	SoapService service;
	
	@GetMapping()
	public TempResponse getFarenheit(@RequestBody TempRequest request) {
		return service.convertCelsiusToFarenheit(request);
	}
}
