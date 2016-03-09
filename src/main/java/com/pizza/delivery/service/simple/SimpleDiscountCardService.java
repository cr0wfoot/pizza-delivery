package com.pizza.delivery.service.simple;

import com.pizza.delivery.domain.entities.Customer;
import com.pizza.delivery.domain.entities.DiscountCard;
import com.pizza.delivery.repository.DiscountCardRepository;
import com.pizza.delivery.service.CustomerService;
import com.pizza.delivery.service.DiscountCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SimpleDiscountCardService implements DiscountCardService {

    @Autowired
    private DiscountCardRepository discountCardRepository;
    
    @Autowired
    private CustomerService simpleCustomerService;

    @Override
    @Transactional
    public void withdrawPoints(DiscountCard card, Integer points) {
        if(card == null || points == null) return;
        if(card.getPoints() <= points) {
            card.setPoints(0);
        } else {
            card.setPoints(card.getPoints() - points);
        }      
        discountCardRepository.update(card);
    }

    @Override
    @Transactional
    public void chargePoints(DiscountCard card, Integer points) {
        if(card == null || points == null) return;
        card.setPoints(card.getPoints() + points);
        discountCardRepository.update(card);
    }

    @Override
    @Transactional
    public void createDiscountCardForCustomer(Customer customer) {
        if(customer == null) return;
        DiscountCard newCard = new DiscountCard();
        Long newCardId = discountCardRepository.create(newCard);
        newCard.setId(newCardId);
        customer.setCard(newCard);
        simpleCustomerService.update(customer);
    }

    @Override
    @Transactional
    public void removeDiscountCardFromCustomer(Customer customer) {
        if(customer == null || !customer.isDiscountCardExists()) return;
        Long cardId = customer.getCard().getId();
        customer.setCard(null);
        simpleCustomerService.update(customer);
        discountCardRepository.delete(cardId);
    }
    
}