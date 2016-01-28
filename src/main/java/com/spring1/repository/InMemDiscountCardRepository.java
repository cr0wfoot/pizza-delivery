package com.spring1.repository;

import com.spring1.domain.DiscountCard;
import java.util.HashMap;
import java.util.Map;

public class InMemDiscountCardRepository implements DiscountCardRepository {

    private final Map<Integer, DiscountCard> cards = new HashMap<Integer, DiscountCard>();
    
    public void update(DiscountCard card) {
        cards.put(card.getId(), card);
    }

    public DiscountCard find(Integer id) {
        return cards.get(id);
    }
    
}
