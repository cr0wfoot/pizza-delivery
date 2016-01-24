package com.spring1;

import com.spring1.service.SimpleOrderService;
import com.spring1.domain.Customer;
import com.spring1.domain.Order;

public class DeliveryApp {
    public static void main( String[] args ) {
        Customer customer = new Customer();        
        
        SimpleOrderService orderService = new SimpleOrderService();
        Order order = orderService.placeNewOrder(customer, 1, 2, 3);
        
        System.out.println(order);
    }
}
