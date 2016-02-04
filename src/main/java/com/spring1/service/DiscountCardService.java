package com.spring1.service;

import com.spring1.domain.DiscountCard;

public interface DiscountCardService {
    
    void withdrawPoints(DiscountCard card, Double points);
    
    void chargePoints(DiscountCard card, Double points);
    
}
