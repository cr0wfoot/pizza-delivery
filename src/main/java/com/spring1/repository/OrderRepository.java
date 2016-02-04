package com.spring1.repository;

import com.spring1.domain.Order;

public interface OrderRepository {
    
    Integer insert(Order newOrder);
    
    Order find(Integer id);
    
    void save(Order order);
    
}
