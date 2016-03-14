package com.pizza.delivery.service;

import com.pizza.delivery.domain.Basket;
import com.pizza.delivery.domain.OrderState;
import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.PizzaOrder;
import java.util.List;

/**
 * Interacts with part of repository's layer which is responsible for order.
 * @see PizzaOrder
 * @see OrderState
 * @see CustomerDTO
 * @see Basket
 */
public interface OrderService {

    /**
     * Change state of existing order
     * @param orderId long value of id
     * @param newState the value of state to change for
     */
    void changeStateOfOrder(Long orderId, OrderState newState);
    
    /**
     * Place new order with data from basket and CustomerDTO
     * @param customerDto an object of class CustomerDTO
     * @param basketOfPizzas an object of class Basket with pizzas
     */
    void placeNewOrder(CustomerDTO customerDto, Basket basketOfPizzas);
    
    /**
     * Add pizzas to existing order
     * @param orderId the id of order
     * @param basketOfPizzas an object of class Basket with pizzas
     */
    void addPizzasToOrder(Long orderId, Basket basketOfPizzas);
    
    /**
     * Get all orders
     * @return the list of orders
     */
    List<PizzaOrder> showOrders();
    
    /**
     * Find order by given id
     * @param id the value of id
     * @return an object of class PizzaOrder
     */
    PizzaOrder showOrder(Long id);
    
    /**
     * Find order by given id with possibility to load all fetch fields of PizzaOrder
     * @param id the value of id
     * @param fetchLazy true - fetch lazy, false - fetch eager.
     * @return an object of class PizzaOrder
     */
    PizzaOrder showOrder(Long id, boolean fetchLazy);
    
}