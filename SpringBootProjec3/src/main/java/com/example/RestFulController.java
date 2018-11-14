
package com.example;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class RestFulController {
    
	@RequestMapping(value = "/ex/foos", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String hello() {
		return "Howdy " + ", have a good day";
	}

	@RequestMapping("/get/{name}")
	public String get(@PathVariable String name) {
		return "Howdy " + name + ", have a good day";
	}
}
