package com.fintech.banking_service.controller;

import com.fintech.banking_service.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Greeting sayHello() {
        // Here we return the "Model" object
        return new Greeting("Hello from the Fintech Backend!", "ACTIVE");
    }
}