package com.spring1.service;

import com.spring1.domain.Pizza;
import com.spring1.repository.PizzaRepository;

public class SimplePizzaService implements PizzaService {

    private final PizzaRepository pizzaRepository;

    public SimplePizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }
    
    public Pizza fing(Integer id) {
        return pizzaRepository.find(id);
    }
    
}
