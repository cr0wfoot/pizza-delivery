package com.pizza.delivery.domain.discounts;

import com.pizza.delivery.domain.entities.OrderDetails;
import com.pizza.delivery.domain.entities.PizzaOrder;
import java.util.Set;

public class DiscountForTheBiggestPizzaInOrder implements Discount {

    private static final int PIZZAS_COUNT_FOR_DISCOUNTS = 4;
    private static final double DISCOUNT_FOR_MAX_PIZZA_COEFF = 0.3;
    
    @Override
    public Double calculate(PizzaOrder order) {
        if(order == null || order.getDetails().size() < PIZZAS_COUNT_FOR_DISCOUNTS) {
            return 0.;
        }
        OrderDetails detail = findPizzaWithMaxPrice(order.getDetails());
        return detail.getPrice() * detail.getPizzasQuantity() * DISCOUNT_FOR_MAX_PIZZA_COEFF;
    }
    
    private OrderDetails findPizzaWithMaxPrice(Set<OrderDetails> details) {
        Double maxPrice = 0.;
        OrderDetails currentPizza = null;
        for(OrderDetails d : details) {
            if(d.getPrice() > maxPrice) {
                maxPrice = d.getPrice();
                currentPizza = d;
            }
        }
        return currentPizza;
    }
    
}