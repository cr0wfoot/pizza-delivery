package com.pizza.delivery.service;

import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.Customer;
import java.util.List;

public interface CustomerService {
    
    Customer find(Long id);
    
    List<Customer> findAll();
    
    void update(Customer customer);
    
    Customer getCustomerFromDTO(CustomerDTO CustomerDto);
    
    Customer createNewCustomer(CustomerDTO customerDto);
    
    void delete(Long id);
    
}