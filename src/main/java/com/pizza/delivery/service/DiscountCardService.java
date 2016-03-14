package com.pizza.delivery.service;

import com.pizza.delivery.domain.entities.Customer;
import com.pizza.delivery.domain.entities.DiscountCard;

/**
 * Interacts with part of repository's layer which is responsible for customer's discount card.
 * @see Customer
 * @see DiscountCard
 */
public interface DiscountCardService {
    
    /**
     * Create discount card for customer
     * @param customer an object of class Customer
     */
    void createDiscountCardForCustomer(Customer customer);
    
    /**
     * Withdraw points from discount card
     * @param card an object of class DiscountCard
     * @param points the int value of points to withdraw
     */
    void withdrawPoints(DiscountCard card, Integer points);
    
    /**
     * Charge points on discount card
     * @param card an object of class DiscountCard
     * @param points the int value of points to charge
     */
    void chargePoints(DiscountCard card, Integer points);
    
    /**
     * Remove discount card from customer
     * @param customer an object of class Customer
     */
    void removeDiscountCardFromCustomer(Customer customer);
    
}