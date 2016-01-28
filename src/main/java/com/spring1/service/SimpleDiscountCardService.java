package com.spring1.service;

import com.spring1.domain.DiscountCard;
import com.spring1.repository.DiscountCardRepository;

public class SimpleDiscountCardService implements DiscountCardService {

    private final DiscountCardRepository discountCardRepository;

    public SimpleDiscountCardService(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }
    
    public void updatePoints(DiscountCard card, Double pointsToAdd) {
        card.setPoints(card.getPoints() + pointsToAdd);
        discountCardRepository.update(card);
    }
    
    public DiscountCard find(Integer id) {
        return discountCardRepository.find(id);
    }
    
}
