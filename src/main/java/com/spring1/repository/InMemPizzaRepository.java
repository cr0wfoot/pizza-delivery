package com.spring1.repository;

import com.spring1.domain.Pizza;
import java.util.HashMap;
import java.util.Map;

public class InMemPizzaRepository implements PizzaRepository {

    private static final Map<Integer, Pizza> pizzas = new HashMap<Integer, Pizza>();
    
    static {
        pizzas.put(1, new Pizza(1, "sea", 1.0, Pizza.Type.SEA));
        pizzas.put(2, new Pizza(2, "meat", 1.0, Pizza.Type.MEAT));
        pizzas.put(3, new Pizza(3, "vegetarian", 1.0, Pizza.Type.VEGETARIAN));
    }
    
    public Pizza save(Pizza newPizza) {
        pizzas.put(newPizza.getId(), newPizza);
        return newPizza;
    }

    public Pizza find(Integer id) {
        return pizzas.get(id);
    }
    
}
