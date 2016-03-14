package com.pizza.delivery.repository;

import com.pizza.delivery.domain.entities.Customer;
import java.util.List;

/**
 * Contains operations to interact with part of database which is responsible for customer
 * @see Customer
 */
public interface CustomerRepository {
    
    /**
     * Creates customer in database, returns id of created customer
     * @param customer an object of class Customer
     * @return id of created customer
     */
    Long create(Customer customer);
    
    /**
     * Get customer from database by id
     * @param id the value of id
     * @return an object of class Customer
     */
    Customer read(Long id);
    
    /**
     * Overload, get customer from database by id with possibility to load all fetch fields of customer
     * @param id the value of id
     * @param fetchLazy true - fetch lazy, false - fetch eager.
     * @return an object of class Customer
     */
    Customer read(Long id, boolean fetchLazy);
    
    /**
     * Updates customer in database
     * @param customer an object of class Customer
     */
    void update(Customer customer);
    
    /**
     * Get all customers from database
     * @return the list of customers
     */
    List<Customer> readAll();
    
    /**
     * Remove customer from database by customer's id
     * @param id the value of id
     */
    void delete(Long id);
    
}