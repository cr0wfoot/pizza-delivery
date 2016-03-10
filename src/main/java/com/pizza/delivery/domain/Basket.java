package com.pizza.delivery.domain;

import com.pizza.delivery.domain.entities.Pizza;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class Basket {
    
    private Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();
    
    public Double getPrice() {
        Double totalPrice = 0d;
        for(Entry<Pizza, Integer> e : pizzas.entrySet()) {
            totalPrice += e.getKey().getPrice() * e.getValue();
        }
        return totalPrice;
    }
    
    public int getQuantity() {
        int sum = 0;
        for(Integer i : pizzas.values()) {
            sum += i;
        }
        return sum;
    }
    
    public Map<Pizza, Integer> getPizzas() {
        return this.pizzas;
    }
    
    public void add(Pizza pizza) {
        if(pizza == null) return;
        if (pizzas.containsKey(pizza)) {
            pizzas.put(pizza, pizzas.get(pizza) + 1);
        } else {
            pizzas.put(pizza, 1);
        }
    }
    
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
    
    public void clear() {
        pizzas.clear();
    }
    
}