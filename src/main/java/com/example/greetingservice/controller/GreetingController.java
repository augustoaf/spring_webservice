package com.example.greetingservice.controller;

import com.example.greetingservice.model.Person;
import com.example.greetingservice.service.GreetingService;
import com.example.greetingservice.model.GreetingResponse;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    private final GreetingService greetingService;

    // Spring automatically injects the GreetingService instance here due @Service annotation in the class
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        
        return greetingService.sayHello(name); 
    }

    @PostMapping("/greetingsFullName")
    public GreetingResponse greetingsFullName(@Valid @RequestBody Person person) {
        
        logger.debug("debug mode: request received");
         
        try {

            return greetingService.returnGreetings(person);

        } catch (Exception e) {
            logger.error("error caught on controller", e);
            //throwing exception in the controller, Spring will take over and consider the response 
            //as a HTTP Error 500 with some basic information
            throw e;
        }
    }

    //method not using Spring Boot capabilities to respond
    @PostMapping("/v0/greetingsFullName")
    public ResponseEntity<Object> greetingsFullNameDraft(@Valid @RequestBody Person person) {
        
        logger.debug("debug mode: request received v0");
         
        try {

            GreetingResponse greetingResponse = greetingService.returnGreetings(person);
            return new ResponseEntity<>(greetingResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("error caught on controller v0", e);
            // Create a custom response body
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
            body.put("message", e.getMessage()); 
            body.put("timestamp", new java.util.Date());
            
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 