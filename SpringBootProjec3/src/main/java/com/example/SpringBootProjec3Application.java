package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootProjec3Application extends SpringBootServletInitializer {
    private static Class<SpringBootProjec3Application> applicationClass = SpringBootProjec3Application.class;

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
 
}

@RestController
class SpringBootProjec3Controller {

	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		return "Howdy " + name + ", have a good day";
	}
}