package com.fintech.banking_service.model;

public class Greeting {
    private final String content;
    private final String status;

    public Greeting(String content, String status) {
        this.content = content;
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }
}