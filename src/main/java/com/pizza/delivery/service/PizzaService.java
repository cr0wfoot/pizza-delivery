package com.pizza.delivery.service;

import com.pizza.delivery.domain.entities.Pizza;
import java.util.List;

/**
 * Interacts with part of repository's layer which is responsible for pizza.
 * @see Pizza
 */
public interface PizzaService {
    
    /**
     * Find pizza by given id
     * @param id the value of id
     * @return an object of class Pizza
     */
    Pizza find(Long id);
    
    /**
     * Find all pizzas
     * @return the list of pizzas
     */
    List<Pizza> findAll();
    
    /**
     * Creates new or updates pizza
     * @param customer an object of class Pizza
     */
    void save(Pizza pizza);
    
    /**
     * Remove pizza by id
     * @param id the value of id
     */
    void delete(Long id);
    
}