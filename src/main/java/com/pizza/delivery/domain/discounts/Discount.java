package com.pizza.delivery.domain.discounts;

import com.pizza.delivery.domain.entities.PizzaOrder;

/**
 * Interface represents discount for calculating discount due to order data
 * @see PizzaOrder
 */
public interface Discount {
    
    /**
     * Get discount for order
     * @param order an object of class PizzaOrder
     * @return double value of discount
     */
    Double calculate(PizzaOrder order);
    
}