package com.pizza.delivery.repository;

import com.pizza.delivery.domain.entities.DiscountCard;
import java.util.List;

/**
 * Contains operations to interact with part of database which is responsible for discount card
 * @see DiscountCard
 */
public interface DiscountCardRepository {
    
    /**
     * Creates discount card in database, returns id of created discount card
     * @param customer an object of class DiscountCard
     * @return id of created discount card
     */
    Long create(DiscountCard card);
    
    /**
     * Updates discount card in database
     * @param customer an object of class DiscountCard
     */
    void update(DiscountCard card);
    
    /**
     * Remove discount card from database by discount card's id
     * @param id the value of id
     */
    void delete(Long id);
    
    /**
     * Get discount card from database by id
     * @param id the value of id
     * @return an object of class DiscountCard
     */
    DiscountCard read(Long id);
    
    /**
     * Get all discount cards from database
     * @return the list of discount cards
     */
    List<DiscountCard> readAll();
    
}