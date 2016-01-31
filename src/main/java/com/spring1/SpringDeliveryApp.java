package com.spring1;

import com.spring1.domain.Customer;
import com.spring1.repository.OrderRepository;
import com.spring1.repository.PizzaRepository;
import com.spring1.service.OrderService;
import com.spring1.service.PizzaService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDeliveryApp {
    
    public static void main( String[] args ) {
        ConfigurableApplicationContext repCont = new ClassPathXmlApplicationContext("repositoryConfig.xml");
        ConfigurableApplicationContext appCont = new ClassPathXmlApplicationContext(new String[]{"appConfig.xml"}, repCont);
        PizzaRepository pizzaRepository = (PizzaRepository)repCont.getBean("pizzaRepository");
        
        OrderRepository orderRepository = (OrderRepository) repCont.getBean("orderRepository");
        PizzaService pizzaService = (PizzaService) appCont.getBean("pizzaService");
        OrderService orderService = (OrderService) appCont.getBean("orderService");
        System.out.println(pizzaRepository.find(1));
        
        appCont.close();
    }

    
}
