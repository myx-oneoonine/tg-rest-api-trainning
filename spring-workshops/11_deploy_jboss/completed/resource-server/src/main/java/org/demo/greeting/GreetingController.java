package org.demo.greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

	@GetMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		return "Hello " + name + "!!";
	}
}