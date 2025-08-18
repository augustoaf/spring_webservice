package com.example.greetingservice.controller;

import com.example.greetingservice.model.Person;
import com.example.greetingservice.service.GreetingService;

import jakarta.validation.Valid;

import com.example.greetingservice.model.GreetingResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    private final GreetingService greetingService;

    // Spring automatically injects the GreetingService instance here
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        
        return greetingService.sayHello(name); 
    }

    @PostMapping("/greetingsFullName")
    public GreetingResponse greetingsFullName(@Valid @RequestBody Person person) {
        
        logger.debug("debug mode: request generate greetings received");
         
        try {

            return greetingService.returnGreetings(person);

        } catch (Exception e) {
            logger.error("error generate greetings", e);
            throw e;
        }
    }
} 