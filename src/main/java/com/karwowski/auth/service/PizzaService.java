package com.karwowski.auth.service;

import com.karwowski.auth.model.Pizza;
import com.karwowski.auth.model.User;
import com.karwowski.auth.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

/**
 * Created by PK on 2016-11-13.
 */
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;
    public void save(Pizza pizza) {
        pizzaRepository.save(pizza);
    }
    public Pizza findByName(String name){
        return pizzaRepository.findByName(name);
    }
}
