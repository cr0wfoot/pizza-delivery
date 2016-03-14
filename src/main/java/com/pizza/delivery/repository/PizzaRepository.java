package com.pizza.delivery.repository;

import com.pizza.delivery.domain.entities.Pizza;
import java.util.List;

/**
 * Contains operations to interact with part of database which is responsible for pizza
 * @see Pizza
 */
public interface PizzaRepository {
    
    /**
     * Creates pizza in database, returns id of created pizza
     * @param customer an object of class Pizza
     * @return id of created pizza
     */
    Long create(Pizza pizza);
    
    /**
     * Get pizza from database by id
     * @param id the value of id
     * @return an object of class Pizza
     */
    Pizza read(Long id);
    
    /**
     * Updates pizza in database
     * @param customer an object of class Pizza
     */
    void update(Pizza pizza);
    
    /**
     * Get all pizza from database
     * @return the list of pizza
     */
    List<Pizza> readAll();
    
    /**
     * Remove pizza from database by pizza's id
     * @param id the value of id
     */
    void delete(Long id);
        
}