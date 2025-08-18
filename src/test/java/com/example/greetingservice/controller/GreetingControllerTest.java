package com.example.greetingservice.controller;

import com.example.greetingservice.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(GreetingController.class)
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void greetingWithName() throws Exception {
        mockMvc.perform(get("/greeting").param("name", "Alice"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Alice! Welcome to our microservice!"));
    }

    @Test
    void greetingWithoutName() throws Exception {
        mockMvc.perform(get("/greeting"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World! Welcome to our microservice!"));
    }

    @Test
    void greetingsFullName() throws Exception {
        Person person = new Person("John", "Doe");
        String personJson = objectMapper.writeValueAsString(person);

        mockMvc.perform(post("/greetingsFullName")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.englishGreeting").value("Hello, John Doe! Welcome to our microservice!"))
                .andExpect(jsonPath("$.frenchGreeting").value("Bonjour, John Doe! Bienvenue dans notre microservice!"));
    }
} 