package com.example.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class HelloworldSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloworldSecurityApplication.class, args);
	}
}
