package com.spring1.service;

import com.spring1.domain.*;
import com.spring1.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;

public class SimpleOrderService implements OrderService {
    
    private final OrderRepository orderRepository;
    private final PizzaService pizzaService;

    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
    }
    
    @Override
    public boolean changeStateOfOrder(Order order, OrderState newState) {
        
        order.setState(newState);
        saveChanges(order);
        
        return order.getState().equals(newState);
        
    }
    
    @Override
    public boolean placeNewOrder(Customer customer, Integer ... pizzasId) {
       
        DiscountManager discountManager = new DiscountManager();
        List<Pizza> pizzas = getPizzasByIds(pizzasId);
                
        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        newOrder.setState(OrderState.NEW);
        newOrder.setPizzas(pizzas);
        
        discountManager.checkAndSetDiscountForTheBiggestPizza(newOrder);
        discountManager.checkAndSetDiscountForDiscountCard(newOrder);
        discountManager.addPointsOnDiscountCard(newOrder);
                
        createOrder(newOrder); 
        
        return true;
        
    }
    
    @Override
    public boolean addPizzasToOrder(Order order, Integer ... pizzasId) {
        
        DiscountManager discountManager = new DiscountManager();
        List<Pizza> pizzas = getPizzasByIds(pizzasId);
        
        order.addPizzas(pizzas);
        
        discountManager.withdrawPointsFromDiscountCard(order);
        discountManager.checkAndSetDiscountForTheBiggestPizza(order);
        discountManager.checkAndSetDiscountForDiscountCard(order);
        discountManager.addPointsOnDiscountCard(order);
        
        saveChanges(order);
        
        return true;
    }

    private void createOrder(Order newOrder) {
        orderRepository.insert(newOrder);
    }
    
    private void saveChanges(Order order) {
        orderRepository.save(order);
    }
    
    private List<Pizza> getPizzasByIds(Integer...pizzasIds) {
        List<Pizza> pizzas = new ArrayList<Pizza>();
        for(Integer id : pizzasIds)
            pizzas.add(pizzaService.fing(id));
        return pizzas;
    }
    
}
