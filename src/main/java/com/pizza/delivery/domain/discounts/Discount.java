package com.pizza.delivery.domain.discounts;

import com.pizza.delivery.domain.entities.PizzaOrder;

public interface Discount {
    
    Double calculate(PizzaOrder order);
    
}