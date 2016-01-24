package com.spring1.repository;

import com.spring1.domain.Order;
import java.util.HashMap;
import java.util.Map;

public class InMemOrderRepository implements OrderRepository {

    private final Map<Integer, Order> orders = new HashMap<Integer, Order>();
    
    public Order save(Order newOrder) {
        orders.put(newOrder.getId(), newOrder);
        return newOrder;
    }
    
}
