package com.spring1.repository;

import com.spring1.domain.Order;

public interface OrderRepository {
    
    Order save(Order newOrder);
    
}
