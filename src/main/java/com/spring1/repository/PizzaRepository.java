package com.spring1.repository;

import com.spring1.context.annotations.BenchMark;
import com.spring1.context.annotations.PostCreate;
import com.spring1.domain.Pizza;

public interface PizzaRepository {
    
    Pizza save(Pizza newPizza);
    
    @BenchMark
    Pizza find(Integer id);
    
}
