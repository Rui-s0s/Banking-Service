package com.fintech.banking_service.repository;

import com.fintech.banking_service.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
    // This interface is empty, but it gives you .save(), .findAll(), .delete() for free.
}