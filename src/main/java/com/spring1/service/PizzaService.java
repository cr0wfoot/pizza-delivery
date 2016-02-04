package com.spring1.service;

import com.spring1.context.annotations.BenchMark;
import com.spring1.domain.Pizza;

public interface PizzaService {
    
    @BenchMark
    Pizza find(Integer id);
    
}
