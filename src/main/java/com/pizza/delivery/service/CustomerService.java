package com.pizza.delivery.service;

import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.Customer;
import java.util.List;

/**
 * Interacts with part of repository's layer which is responsible for customer.
 * @see Customer
 * @see CustomerDTO
 */
public interface CustomerService {
    
    /**
     * Find customer by given id
     * @param id the value of id
     * @return an object of class Customer
     */
    Customer find(Long id);
    
    /**
     * Find customer by given id with possibility to load all fetch fields of Customer
     * @param id the value of id
     * @param fetchLazy true - fetch lazy, false - fetch eager.
     * @return an object of class Customer
     */
    Customer find(Long id, boolean fetchLazy);
    
    /**
     * Find all customers
     * @return the list of customers
     */
    List<Customer> findAll();
    
    /**
     * Updates customer
     * @param customer an object of class Customer
     */
    void update(Customer customer);
    
    /**
     * Get Customer object from information given by DTO
     * @param CustomerDto the object of CustomerDTO
     * @return an object of class Customer
     */
    Customer getCustomerFromDTO(CustomerDTO CustomerDto);
    
    /**
     * Create new Customer
     * @param customerDto the object of CustomerDTO
     * @return an object of class Customer
     */
    Customer createNewCustomer(CustomerDTO customerDto);
    
    /**
     * Remove customer by id
     * @param id the value of id
     */
    void delete(Long id);
    
}