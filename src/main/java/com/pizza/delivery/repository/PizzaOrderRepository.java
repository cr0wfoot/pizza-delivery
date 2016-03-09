package com.pizza.delivery.repository;

import com.pizza.delivery.domain.entities.PizzaOrder;
import java.util.List;

public interface PizzaOrderRepository {
    
    Long create(PizzaOrder newOrder);
    
    PizzaOrder read(Long id);
    
    void update(PizzaOrder order);
    
    List<PizzaOrder> readAll();
    
    void delete(Long id);
    
}