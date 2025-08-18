package com.example.greetingservice.model;

public class GreetingResponse {
    private String englishGreeting;
    private String frenchGreeting;

    // Default constructor
    public GreetingResponse() {}

    // Constructor with parameters
    public GreetingResponse(String englishGreeting, String frenchGreeting) {
        this.englishGreeting = englishGreeting;
        this.frenchGreeting = frenchGreeting;
    }

    // Getters and setters
    public String getEnglishGreeting() {
        return englishGreeting;
    }

    public void setEnglishGreeting(String englishGreeting) {
        this.englishGreeting = englishGreeting;
    }

    public String getFrenchGreeting() {
        return frenchGreeting;
    }

    public void setFrenchGreeting(String frenchGreeting) {
        this.frenchGreeting = frenchGreeting;
    }
} 