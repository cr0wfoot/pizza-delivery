package com.spring1;

import com.spring1.service.SimpleOrderService;
import com.spring1.domain.Customer;
import com.spring1.domain.Order;
import com.spring1.context.ioc.ApplicationContext;
import com.spring1.context.ioc.JavaConfigApplicationContext;
import com.spring1.repository.InMemOrderRepository;
import com.spring1.repository.InMemPizzaRepository;
import com.spring1.repository.OrderRepository;
import com.spring1.repository.PizzaRepository;
import com.spring1.service.OrderService;
import com.spring1.service.PizzaService;
import com.spring1.service.SimplePizzaService;
import com.spring1.context.config.JavaConfig;

public class DeliveryApp {
    public static void main( String[] args ) throws Exception {
        Customer customer = new Customer();  
        /*
        OrderRepository orderRepository = new InMemOrderRepository();
        PizzaRepository pizzaRepository = new InMemPizzaRepository();
        PizzaService pizzaService = new SimplePizzaService(pizzaRepository);
         
        SimpleOrderService orderService = new SimpleOrderService(orderRepository, pizzaService);
        Order order = orderService.placeNewOrder(customer, 1, 2, 3);
        
        System.out.println(order);*/
        
        ApplicationContext context = new JavaConfigApplicationContext(new JavaConfig());
        OrderRepository orderRepository = (OrderRepository) context.getBean("orderRepository");
        PizzaRepository pizzaRepository = (PizzaRepository) context.getBean("pizzaRepository");
        PizzaService pizzaService = (PizzaService) context.getBean("pizzaService");
        System.out.println(pizzaRepository.find(3));
        //OrderService orderService = (OrderService) context.getBean("orderService");
    }
}
