package com.fintech.banking_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {
 
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleJsonErrors(HttpMessageNotReadableException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 400);

        // Instead of strict class checks, let's look at the behavior
        String errorDetail = ex.getMessage();
        
        if (errorDetail.contains("Cannot construct instance") || errorDetail.contains("Unexpected character")) {
            // This catches the missing brace/comma issues
            response.put("error", "Malformed JSON");
            response.put("message", "The JSON structure is invalid. Check your braces and commas.");
        } 
        else if (errorDetail.contains("GreetingStatus")) {
            response.put("error", "Invalid Status");
            response.put("message", "Status must be PENDING, ACTIVE, or ARCHIVED.");
        }
        else {
            response.put("error", "Bad Request");
            response.put("message", "Request body is unreadable.");
        }

        
        log.warn("Handled JSON Error:\n{}", errorDetail);

        return ResponseEntity.badRequest().body(response);
    }
}

