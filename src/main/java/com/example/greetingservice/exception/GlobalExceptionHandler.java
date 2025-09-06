package com.example.greetingservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

//this class will intercept all exceptions declared here instead of controller handling it
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(
        HttpMessageNotReadableException ex,
        HttpServletRequest request) {

        String errorMessage;
        String requestURI = request.getRequestURI();

        // checking URI in case need to customize the message per endpoint
        if (requestURI.equals("/greetingsFullName")) {
            errorMessage = "Invalid request body for the " + requestURI + " endpoint. "
                    + "Please ensure you provide valid JSON for a Person object (firstName, lastName).";
        } else {
            errorMessage = "Invalid request body for the " + requestURI + " endpoint. ";
        }

        // create a custom response body
        Map<String, String> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.toString());
        body.put("Exception intercepted (HttpMessageNotReadableException), message: ", errorMessage);
        body.put("timestamp", new java.util.Date().toString());

        // Return a ResponseEntity with the custom body and status code
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
        MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
        
        // Create a custom response body
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        body.put("Exception intercepted (NullPointerException), message: ", ex.getMessage()); 
        body.put("timestamp", new java.util.Date());
        
        // Return a ResponseEntity with the custom body and status code
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
