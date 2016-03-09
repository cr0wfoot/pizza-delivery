package com.pizza.delivery.service;

import com.pizza.delivery.domain.entities.Pizza;
import java.util.List;

public interface PizzaService {
    
    Pizza find(Long id);
    
    List<Pizza> findAll();
    
    void save(Pizza pizza);
    
    void delete(Long id);
    
}