package com.fintech.banking_service.controller;

import com.fintech.banking_service.model.Greeting;
import com.fintech.banking_service.repository.GreetingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 1. Start the server on a random port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private GreetingRepository repository; // Inject the "Filing Cabinet"

    private final HttpClient client = HttpClient.newHttpClient();

    // 2. This runs BEFORE EVERY @Test method
    @BeforeEach
    public void setup() {
        repository.deleteAll(); // Clear the DB so tests don't leak into each other
        repository.save(new Greeting("Initial Hello")); // Seed data
    }

    @Test
    public void shouldReturnGreetingsList() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/greetings"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("Initial Hello"));
    }

    @Test
    public void shouldCreateNewGreeting() throws Exception {
        // Using Java Text Blocks (triple quotes) for clean JSON
        String jsonBody = """
                {
                    "message": "New Greeting via POST"
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/greetings"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // We check for 200 or 201 depending on your Controller's return
        assertEquals(200, response.statusCode());
        
        // Verify it actually saved to the database
        assertEquals(2, repository.count()); 
    }
}