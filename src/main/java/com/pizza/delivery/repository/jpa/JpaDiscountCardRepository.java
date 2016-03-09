package com.pizza.delivery.repository.jpa;

import com.pizza.delivery.domain.entities.DiscountCard;
import com.pizza.delivery.repository.DiscountCardRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JpaDiscountCardRepository implements DiscountCardRepository {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional
    public Long create(DiscountCard card) {
        em.persist(card);
        em.flush();
        return card.getId();
    }

    @Override
    @Transactional
    public void update(DiscountCard card) {
        if(card.getId() <= 0) {
            return;
        }
        em.merge(card);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(id == null || id <= 0) {
            return;
        }
        em.createNamedQuery("DiscountCard.deleteById").setParameter("id", id).executeUpdate();
    }

    @Override
    public DiscountCard read(Long id) {
        if(id == null || id <= 0) {
            return null;
        }
        return em.find(DiscountCard.class, id);
    }

    @Override
    public List<DiscountCard> readAll() {
        return em.createNamedQuery("DiscountCard.getAll", DiscountCard.class).getResultList();
    }
    
}