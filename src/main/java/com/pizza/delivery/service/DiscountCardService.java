package com.pizza.delivery.service;

import com.pizza.delivery.domain.entities.Customer;
import com.pizza.delivery.domain.entities.DiscountCard;

public interface DiscountCardService {
    
    void createDiscountCardForCustomer(Customer customer);
    
    void withdrawPoints(DiscountCard card, Integer points);
    
    void chargePoints(DiscountCard card, Integer points);
    
    void removeDiscountCardFromCustomer(Customer customer);
    
}