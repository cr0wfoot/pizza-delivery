package com.spring1.service;

import com.spring1.domain.Customer;
import com.spring1.domain.Order;
import com.spring1.domain.OrderState;
import com.spring1.domain.Pizza;
import com.spring1.repository.OrderRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleOrderService implements OrderService {
    
    private final OrderRepository orderRepository;
    private final PizzaService pizzaService;
    private final DiscountCardService discountCardService;
    
    private static final int MIN_PIZZAS_COUNT = 1;
    private static final int MAX_PIZZAS_COUNT = 10;
    private static final double PIZZAS_COUNT_FOR_DISCOUNTS = 4;
    private static final double DISCOUNT_FOR_MAX_PIZZA_COEFF = 0.7;
    private static final double DISCOUNT_FOR_CARD_POINTS_COEFF = 0.1;
    private static final double MAX_AVALIABLE_POINTS_DISCOUNT_COEFF = 0.3;

    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService, DiscountCardService discountCardService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
        this.discountCardService = discountCardService;
    }
    
    public boolean changeStateOfOrder(Order order, OrderState newState) {
        List<OrderState> avaliableStatesToChange = order.getState().getAvaliableStatesToChange();
        if(avaliableStatesToChange.contains(newState)) {
            order.setState(newState);
            return true;
        } else 
            return false;
    }
    
    @Override
    public boolean placeNewOrder(Customer customer, Integer ... pizzasId) {
       
        List<Pizza> pizzas = getPizzasByIds(pizzasId);
        
        Double totalPrice = calculateTotalPriceWithAllDiscounts(pizzas, customer);
        
        Order newOrder = new Order(customer, pizzas, OrderState.NEW, totalPrice);
        
        sumPriceToDiscountCard(customer, totalPrice);
        
        saveOrder(newOrder); 
        
        return true;
        
    }
    
    public boolean addPizzasToOrder(Order order, Integer ... pizzasId) {
        if(pizzasId.length >= MIN_PIZZAS_COUNT && pizzasId.length <= MAX_PIZZAS_COUNT)
            return false;
        List<Pizza> pizzas = getPizzasByIds(pizzasId);
        order.addPizzas(pizzas);
        orderRepository.update(order);
        return true;
    }
    
    private void sumPriceToDiscountCard(Customer customer, Double price) {
        if(customer.isDiscountCardExists())
            discountCardService.updatePoints(customer.getDiscountCard(), price);
    }
    
    private Double calculateTotalPriceWithAllDiscounts(List<Pizza> pizzas, Customer customer) {
        
        setDiscountsForBigOrder(pizzas);
        
        Double totalPrice = 0.0;
        for(Pizza p : pizzas)
            totalPrice += p.getPrice();
        
        totalPrice = setDiscountForDiscountCardPoints(customer, totalPrice);
        
        return totalPrice;

    }
    
    private void setDiscountsForBigOrder(List<Pizza> pizzas) {
        if(pizzas.size() <= PIZZAS_COUNT_FOR_DISCOUNTS)
            return;
        Collections.sort(pizzas, Pizza.compareByPrice());
        Double maxPrice = pizzas.get(0).getPrice();
        for(Pizza p : pizzas) {
            if(p.getPrice() == maxPrice)
                p.setPrice(maxPrice * DISCOUNT_FOR_MAX_PIZZA_COEFF);
            else break;
        }
    }

    private void saveOrder(Order newOrder) {
        orderRepository.save(newOrder);
    }
    
    private List<Pizza> getPizzasByIds(Integer...pizzasIds) {
        List<Pizza> pizzas = new ArrayList<Pizza>();
        for(Integer id : pizzasIds)
            pizzas.add(pizzaService.fing(id));
        return pizzas;
    }

    private Double setDiscountForDiscountCardPoints(Customer customer, Double totalPrice) {
        Double discountForPoints;
        if(customer.isDiscountCardExists()) {
            discountForPoints = customer.getDiscountCard().getPoints() * DISCOUNT_FOR_CARD_POINTS_COEFF;
            if(discountForPoints > totalPrice * MAX_AVALIABLE_POINTS_DISCOUNT_COEFF)
                 totalPrice -= totalPrice * MAX_AVALIABLE_POINTS_DISCOUNT_COEFF;
            else totalPrice -= discountForPoints;
        }
        return totalPrice;
    }
    
}
