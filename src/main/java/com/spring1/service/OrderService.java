package com.spring1.service;

import com.spring1.domain.Customer;
import com.spring1.domain.Order;

public interface OrderService {

    Order placeNewOrder(Customer customer, Integer... pizzasID);
    
}
