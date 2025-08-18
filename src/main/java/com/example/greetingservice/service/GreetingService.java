package com.example.greetingservice.service;

import org.springframework.stereotype.Service;

import com.example.greetingservice.model.GreetingResponse;
import com.example.greetingservice.model.Person;

//using @Service annotation, means Spring will create this instance just once (singleton) 
//to inject where it is being referenced, so if you have more than one RESTful controller needing this service
//Spring will inject the same instance. You can implement the singleton pattern manually to achieve same result.
//Note: Singleton help on scalability if you have more than one controller using same service, otherwise if you instantiate 
//this service in each controller, you will have 1 service per controller 

@Service
public class GreetingService {

    
    /* 
    //singleton implementation - not needed as class is now annotated with @Service 
    private static final GreetingService INSTANCE = new GreetingService();

    public static GreetingService getInstance() {
        return INSTANCE;
    } */
    

    public GreetingResponse returnGreetings (Person person) {
        
        String firstName = person.getFirstName() != null ? person.getFirstName() : "";
        String lastName = person.getLastName() != null ? person.getLastName() : "";

        String englishGreeting = String.format("Hello, %s %s! Welcome to our microservice!", 
        firstName, lastName);
        
        String frenchGreeting = String.format("Bonjour, %s %s! Bienvenue dans notre microservice!", 
        firstName, lastName);
        
        return new GreetingResponse(englishGreeting, frenchGreeting);
    }

    public String sayHello(String name) {

        return String.format("Hello, %s! Welcome to our microservice!", name);
    }
}
