package com.pizza.delivery.domain.discounts;

import com.pizza.delivery.domain.entities.PizzaOrder;

/**
 * Discount for discount card, 10% of points on user's discount card 
 * or 30% of order price, the minimum value.
 * @see DiscountCard
 */
public class DiscountForDiscountCard implements Discount {

    private static final double DISCOUNT_FOR_CARD_POINTS_COEFF = 0.1;
    private static final double MAX_AVALIABLE_POINTS_DISCOUNT_COEFF = 0.3;
    
    @Override
    public Double calculate(PizzaOrder order) {
        if(order == null || order.getCustomer() == null || !order.getCustomer().isDiscountCardExists()) {
            return 0.;
        }
        Double discount = order.getCustomer().getCard().getPoints() * DISCOUNT_FOR_CARD_POINTS_COEFF;
        return Math.min(discount, order.getTotalPrice() * MAX_AVALIABLE_POINTS_DISCOUNT_COEFF);
    }
    
}