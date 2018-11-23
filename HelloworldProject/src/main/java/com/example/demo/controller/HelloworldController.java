package com.example.demo.controller;

import com.example.demo.model.ObjectRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/helloWorld")
public class HelloworldController {

    @GetMapping(value = "get")
    public String getHelloWorld() {
        return "Hello world!";
    }

    @PostMapping(value = "post")
    public String postHelloWorld() {
        return "Hello world!";
    }

    @PostMapping(value = "postReqBody")
    public String helloByName(@RequestBody String name) {
        return "Hello " + name;
    }

    @PostMapping(value = "postReqJson")
    public String helloByJson(@RequestBody ObjectRequest request) {
        return "Hello " + request.getName();
    }
}
