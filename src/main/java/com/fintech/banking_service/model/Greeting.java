package com.fintech.banking_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;



@Entity // This tells Spring: "Create a database table for this class"
public class Greeting {

    public enum GreetingStatus {
        PENDING, ACTIVE, ARCHIVED
    }

    @Id // Sets this as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;

    @NotBlank(message = "Message cannot be empty")
    private String message;
    @Enumerated(EnumType.STRING)
    private GreetingStatus status = GreetingStatus.PENDING;

    // --- BOILERPLATE SECTION ---

    public Greeting() {} // Needed by the database

    public Greeting(String message) {
        this.message = message;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public GreetingStatus getStatus() { return status; }
    public void setStatus(GreetingStatus status) { this.status = status; }
}