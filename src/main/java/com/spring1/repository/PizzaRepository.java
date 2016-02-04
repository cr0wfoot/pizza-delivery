package com.spring1.repository;

import com.spring1.domain.Pizza;

public interface PizzaRepository {
    
    Pizza find(Integer id);
    
}
