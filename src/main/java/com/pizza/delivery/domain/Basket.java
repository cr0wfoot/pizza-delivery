package com.pizza.delivery.domain;

import com.pizza.delivery.domain.entities.Pizza;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A shopping basket to store chosen pizza, contains methods for adding and
 * removing pizza, and methods calculating total price and quantity.
 * @see Pizza
 */
@Component
@Scope("session")
public class Basket {
    
    private Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();
    
    /**
     * Calculate and show total price of order without any discounts
     * @return double value of total price
     */
    public Double getPrice() {
        Double totalPrice = 0d;
        for(Entry<Pizza, Integer> e : pizzas.entrySet()) {
            totalPrice += e.getKey().getPrice() * e.getValue();
        }
        return totalPrice;
    }
    
    /**
     * Calculate and show total quantity of pizzas in basket
     * @return int value of quantity
     */
    public int getQuantity() {
        int sum = 0;
        for(Integer i : pizzas.values()) {
            sum += i;
        }
        return sum;
    }
    
    /**
     * Get all pizzas in basket
     * @return the map of pizzas
     */
    public Map<Pizza, Integer> getPizzas() {
        return this.pizzas;
    }
    
    /**
     * Add pizza to basket
     * @param pizza an object of class Pizza
     */
    public void add(Pizza pizza) {
        if(pizza == null) return;
        if (pizzas.containsKey(pizza)) {
            pizzas.put(pizza, pizzas.get(pizza) + 1);
        } else {
            pizzas.put(pizza, 1);
        }
    }
    
    /**
     * Remove pizza from basket
     * @param pizza an object of class Pizza
     */
    public void remove(Pizza pizza) {
        if(!pizzas.containsKey(pizza)) {
            return;
        }
        if(pizzas.get(pizza) > 1) {
            pizzas.put(pizza, pizzas.get(pizza) - 1);
        } else {
            pizzas.remove(pizza);
        }
    }
    
    /**
     * Clear basket
     */
    public void clear() {
        pizzas.clear();
    }
    
}