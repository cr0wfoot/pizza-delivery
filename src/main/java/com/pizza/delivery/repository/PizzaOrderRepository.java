package com.pizza.delivery.repository;

import com.pizza.delivery.domain.entities.PizzaOrder;
import java.util.List;

/**
 * Contains operations to interact with part of database which is responsible for order
 * @see PizzaOrder
 */
public interface PizzaOrderRepository {
    
    /**
     * Creates order in database, returns id of created order
     * @param customer an object of class Order
     * @return id of created order
     */
    Long create(PizzaOrder newOrder);
    
    /**
     * Get order from database by id
     * @param id the value of id
     * @return an object of class Order
     */
    PizzaOrder read(Long id);
    
    /**
     * Overload, get order from database by id with possibility to load all fetch fields of order
     * @param id the value of id
     * @param fetchLazy true - fetch lazy, false - fetch eager.
     * @return an object of class Order
     */
    PizzaOrder read(Long id, boolean fetchLazy);
    
    /**
     * Updates order in database
     * @param customer an object of class Order
     */
    void update(PizzaOrder order);
    
    /**
     * Get all orders from database
     * @return the list of orders
     */
    List<PizzaOrder> readAll();
    
    /**
     * Remove order from database by order's id
     * @param id the value of id
     */
    void delete(Long id);
    
}