/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring1;

import com.spring1.repository.PizzaRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Alex
 */
public class SpringDeliveryApp {
    
    public static void main( String[] args ) {
        ConfigurableApplicationContext appCont = new ClassPathXmlApplicationContext("appConfig.xml");
        PizzaRepository pizzaRepository = (PizzaRepository)appCont.getBean("pizzaRepository");
        System.out.println(pizzaRepository.find(1));
    }

    
}
