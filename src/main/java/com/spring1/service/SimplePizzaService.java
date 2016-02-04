package com.spring1.service;

import com.spring1.context.annotations.BenchMark;
import com.spring1.domain.Pizza;
import com.spring1.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimplePizzaService implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @BenchMark
    public Pizza find(Integer id) {
        return pizzaRepository.find(id);
    }
    
}
