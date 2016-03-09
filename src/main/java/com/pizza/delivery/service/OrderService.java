package com.pizza.delivery.service;

import com.pizza.delivery.domain.Basket;
import com.pizza.delivery.domain.OrderState;
import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.PizzaOrder;
import java.util.List;

public interface OrderService {

    void changeStateOfOrder(Long orderId, OrderState newState);
    
    void placeNewOrder(CustomerDTO customerDto, Basket basketOfPizzas);
    
    void addPizzasToOrder(Long orderId, Basket basketOfPizzas);
    
    List<PizzaOrder> showOrders();
    
}