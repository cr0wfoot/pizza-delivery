package com.spring1.service;

import com.spring1.domain.Customer;
import com.spring1.domain.Order;
import com.spring1.domain.Pizza;
import com.spring1.repository.OrderRepository;
import com.spring1.servicelocator.ServiceLocatorFactory;
import java.util.ArrayList;
import java.util.List;

public class SimpleOrderService implements OrderService {
    
    private ServiceLocatorFactory factory= ServiceLocatorFactory.getInstance();
    private OrderRepository orderRepository = (OrderRepository)factory.create("orderRepository");
    private PizzaService pizzaService = (PizzaService)factory.create("pizzaService");
    
    @Override
    public Order placeNewOrder(Customer customer, Integer ... pizzasID) {
       
        List<Pizza> pizzas = new ArrayList<Pizza>();
        for(Integer id : pizzasID) {
            pizzas.add(getPizzaByID(id));  
        }
        Order newOrder = new Order(customer, pizzas);
        saveOrder(newOrder); 
        return newOrder;
        
    }

    private void saveOrder(Order newOrder) {
        orderRepository.save(newOrder);
    }
    
    private Pizza getPizzaByID(Integer id) {
        return pizzaService.fing(id);
    }
    
}
