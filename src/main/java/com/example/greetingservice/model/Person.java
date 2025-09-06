package com.example.greetingservice.model;

import jakarta.validation.constraints.NotBlank;

public class Person {
    
    @NotBlank(message = "First name cannot be empty.")
    private String firstName;
    private String lastName;

    // Default constructor
    public Person() {}

    // Constructor with parameters
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return "Person [firstName=" + firstName + ", lastName=" + lastName + "]";
    }

} 