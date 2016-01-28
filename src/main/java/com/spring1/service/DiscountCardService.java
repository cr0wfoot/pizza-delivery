package com.spring1.service;

import com.spring1.domain.DiscountCard;

public interface DiscountCardService {
    
    void updatePoints(DiscountCard card, Double pointsToAdd);
    
    DiscountCard find(Integer id);
    
}
