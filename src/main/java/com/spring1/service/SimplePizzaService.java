package com.spring1.service;

import com.spring1.domain.Pizza;
import com.spring1.repository.PizzaRepository;
import com.spring1.servicelocator.ServiceLocatorFactory;

public class SimplePizzaService implements PizzaService {

    private ServiceLocatorFactory factory= ServiceLocatorFactory.getInstance();
    private PizzaRepository pizzaRepository = (PizzaRepository)factory.create("pizzaRepository");
    
    public Pizza fing(Integer id) {
        return pizzaRepository.find(id);
    }
    
}
