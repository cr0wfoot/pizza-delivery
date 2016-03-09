package com.pizza.delivery.repository;

import com.pizza.delivery.domain.entities.Pizza;
import java.util.List;

public interface PizzaRepository {
    
    Long create(Pizza pizza);
    
    Pizza read(Long id);
    
    void update(Pizza pizza);
    
    List<Pizza> readAll();
    
    void delete(Long id);
        
}