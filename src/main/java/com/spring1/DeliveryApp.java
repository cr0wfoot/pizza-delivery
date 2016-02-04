package com.spring1;

import com.spring1.context.ioc.config.JavaConfig;
import com.spring1.context.ioc.ApplicationContext;
import com.spring1.context.ioc.JavaConfigApplicationContext;
import com.spring1.domain.Customer;
import com.spring1.repository.OrderRepository;
import com.spring1.repository.PizzaRepository;
import com.spring1.service.OrderService;
import com.spring1.service.PizzaService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeliveryApp {
    public static void main( String[] args ) throws Exception {
        Customer customer = new Customer();          
        ApplicationContext context = new JavaConfigApplicationContext(new JavaConfig());
        OrderRepository orderRepository = (OrderRepository) context.getBean("orderRepository");
        PizzaRepository pizzaRepository = (PizzaRepository) context.getBean("pizzaRepository");
        PizzaService pizzaService = (PizzaService) context.getBean("pizzaService");
        OrderService orderService = (OrderService) context.getBean("orderService");
        System.out.println(pizzaRepository.find(1));
        
    
        
        
    }
}
