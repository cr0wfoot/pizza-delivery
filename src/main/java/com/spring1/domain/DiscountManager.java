package com.spring1.domain;

import java.util.Collections;
import java.util.List;

public class DiscountManager {
    
    private static final int PIZZAS_COUNT_FOR_DISCOUNTS = 4;
    private static final double DISCOUNT_FOR_MAX_PIZZA_COEFF = 0.7;
    private static final double DISCOUNT_FOR_CARD_POINTS_COEFF = 0.1;
    private static final double MAX_AVALIABLE_POINTS_DISCOUNT_COEFF = 0.3;
    
    public void addPointsOnDiscountCard(Order order) {
        DiscountCard card = order.getCustomer().getDiscountCard();
        card.setPoints(card.getPoints() + order.getPrice());
    }
    
    public void checkAndSetDiscountForTheBiggestPizza(Order order) {
        Double newTotalPrice = 0.0;
        List<Pizza> pizzas = order.getPizzas();
        
        if(pizzas.size() <= PIZZAS_COUNT_FOR_DISCOUNTS)
            return;
        
        Collections.sort(pizzas, Pizza.compareByPrice());
        Double maxPrice = pizzas.get(0).getPrice();
        for(Pizza p : pizzas) {
            if(p.getPrice() == maxPrice)
                 newTotalPrice += maxPrice * DISCOUNT_FOR_MAX_PIZZA_COEFF;
            else newTotalPrice += p.getPrice();
        }
        
        order.setPrice(newTotalPrice);
    }
    
    public void checkAndSetDiscountForDiscountCard(Order order) {
        Double totalPrice = order.getPrice();
        
        Double discountForPoints;
        if(order.getCustomer().isDiscountCardExists()) {
            discountForPoints = order.getCustomer().getDiscountCard().getPoints() * DISCOUNT_FOR_CARD_POINTS_COEFF;
            if(discountForPoints > totalPrice * MAX_AVALIABLE_POINTS_DISCOUNT_COEFF)
                 totalPrice -= totalPrice * MAX_AVALIABLE_POINTS_DISCOUNT_COEFF;
            else totalPrice -= discountForPoints;
        }
        
        order.setPrice(totalPrice);
    }
    
    public void withdrawPointsFromDiscountCard(Order order) {
        DiscountCard card = order.getCustomer().getDiscountCard();
        card.setPoints(card.getPoints() - order.getPrice());
    }
    
}
