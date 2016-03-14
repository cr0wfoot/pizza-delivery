package com.pizza.delivery.service.simple;

import com.pizza.delivery.domain.entities.Pizza;
import com.pizza.delivery.repository.PizzaRepository;
import com.pizza.delivery.service.PizzaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Simple implementation, interacts with pizza layer
 * @see Pizza
 * @see PizzaOrderRepository
 */
@Service
public class SimplePizzaService implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public Pizza find(Long id) {
        return pizzaRepository.read(id);
    }
    
    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void save(Pizza pizza) {
        if(pizza.getId() == null) {
            pizzaRepository.create(pizza);
        } else {
            pizzaRepository.update(pizza);
        }
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaRepository.readAll();
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void delete(Long id) {
        pizzaRepository.delete(id);
    }
    
}