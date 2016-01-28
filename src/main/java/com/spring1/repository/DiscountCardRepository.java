package com.spring1.repository;

import com.spring1.domain.DiscountCard;

public interface DiscountCardRepository {
    
    void update(DiscountCard card);
    
    DiscountCard find(Integer id);
    
}
