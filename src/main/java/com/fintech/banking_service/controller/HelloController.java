package com.fintech.banking_service.controller;

import com.fintech.banking_service.model.Greeting;
import com.fintech.banking_service.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/greetings") // All routes in this file start with this
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @Autowired 
    private GreetingRepository repository; // Spring "injects" the database worker here

    // 1. CREATE (POST)
    @PostMapping
    public Greeting createGreeting(@Valid @RequestBody Greeting greeting) {
        // 1. Logs before saving (ID is null)
        log.info("Received request to create: {} status: {}", greeting.getMessage(), greeting.getStatus());

        // 2. Save the object and get the UPDATED version back
        Greeting savedGreeting = repository.save(greeting);

        // 3. Now the ID exists!
        log.info("Saved greeting to DB with ID: {}", savedGreeting.getId());

        return savedGreeting;
    }

    // 2. READ ALL (GET)
    @GetMapping
    public List<Greeting> getAll() {
        List<Greeting> greetings = repository.findAll();
        
        log.info("Retrieved {} greetings: {}", greetings.size(), greetings);

        return greetings;
    }

    // 3. READ ONE (GET)
    @GetMapping("/{id}")
    public Greeting getOne(@PathVariable Long id) {
        Greeting greeting = repository.findById(id).orElse(null);
        
        log.info("Retrieved greeting of id: {} content: {}", id, greeting);

        return greeting;
    }

    // 4. DELETE (DELETE)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Greeting greeting = repository.findById(id).orElse(null);

        log.info("Retrieved greeting of id:{} content:{}", id, greeting);

        repository.deleteById(id);
    }
}