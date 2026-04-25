package com.fintech.banking_service.controller;

import com.fintech.banking_service.model.Greeting;
import com.fintech.banking_service.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/greetings") // All routes in this file start with this
public class HelloController {

    @Autowired 
    private GreetingRepository repository; // Spring "injects" the database worker here

    // 1. CREATE (POST)
    @PostMapping
    public Greeting createGreeting(@RequestBody Greeting greeting) {
        return repository.save(greeting);   
    }

    // 2. READ ALL (GET)
    @GetMapping
    public List<Greeting> getAll() {
        return repository.findAll();
    }

    // 3. READ ONE (GET)
    @GetMapping("/{id}")
    public Greeting getOne(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // 4. DELETE (DELETE)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}