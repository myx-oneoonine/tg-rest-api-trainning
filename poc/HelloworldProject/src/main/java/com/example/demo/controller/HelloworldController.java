package com.example.demo.controller;

import com.example.demo.model.ObjectRequest;
import com.example.demo.repo.Customer;
import com.example.demo.repo.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/helloWorld")
public class HelloworldController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "customer")
    public List<Customer> getCustomers() {
        return customerService.retrieveCustomer();
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
