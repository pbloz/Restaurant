package com.karwowski.auth.service;

import com.karwowski.auth.model.Pizza;
import com.karwowski.auth.model.User;
import com.karwowski.auth.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;
    public void save(Pizza pizza) {
        pizzaRepository.save(pizza);
    }
    public Pizza findByName(String name){
        return pizzaRepository.findByName(name);
    }
    public List<Pizza> allPizzas(){
        return pizzaRepository.findAll();
    }

    public Pizza findById(Long id) {
        return pizzaRepository.findById(id);
    }
    public void removePizza(Long id){
        Pizza pizza= findById(id);
        pizzaRepository.delete(id);
    }
}
