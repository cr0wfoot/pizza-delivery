package com.spring1.service;

import com.spring1.context.annotations.BenchMark;
import com.spring1.domain.Customer;
import com.spring1.domain.Order;
import com.spring1.domain.OrderState;

public interface OrderService {

    boolean changeStateOfOrder(Order order, OrderState newState);
    
    @BenchMark
    boolean placeNewOrder(Customer customer, Integer... pizzasID);
    
    boolean addPizzasToOrder(Order order, Integer ... pizzasId);
    
}
