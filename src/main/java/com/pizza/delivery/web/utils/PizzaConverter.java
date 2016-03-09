package com.pizza.delivery.web.utils;

import com.pizza.delivery.domain.entities.Pizza;
import com.pizza.delivery.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class PizzaConverter implements Converter<String, Pizza> {

    @Autowired
    private PizzaService pizzaService;
    
    public Pizza convert(String id) {
        Pizza pizza;
        if (id == null || id.isEmpty()) {
            pizza = new Pizza();
        } else {
            if(Integer.valueOf(id) <= 0)
                throw new IllegalArgumentException();
            pizza = pizzaService.find(Long.valueOf(id));
        }
        return pizza;
    }

}