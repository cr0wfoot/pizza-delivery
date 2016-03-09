package com.pizza.delivery.repository;

import com.pizza.delivery.domain.entities.DiscountCard;
import java.util.List;

public interface DiscountCardRepository {
    
    Long create(DiscountCard card);
    
    void update(DiscountCard card);
    
    void delete(Long id);
    
    DiscountCard read(Long id);
    
    List<DiscountCard> readAll();
    
}