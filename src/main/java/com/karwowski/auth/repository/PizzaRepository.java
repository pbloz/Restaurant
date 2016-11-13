package com.karwowski.auth.repository;


import com.karwowski.auth.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Pizza findByName(String name);
}
