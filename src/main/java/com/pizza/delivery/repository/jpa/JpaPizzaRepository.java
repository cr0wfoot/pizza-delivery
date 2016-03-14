package com.pizza.delivery.repository.jpa;

import com.pizza.delivery.domain.entities.Pizza;
import com.pizza.delivery.repository.PizzaRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation, interacts with Pizza
 * @see Pizza
 */
@Repository
public class JpaPizzaRepository implements PizzaRepository {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Pizza read(Long id) {
        if(id == null || id <= 0) {
            return null;
        }
        return em.find(Pizza.class, id);
    }

    @Override
    @Transactional
    public void update(Pizza pizza) {
        if(pizza.getId() <= 0) {
            return;
        }
        em.merge(pizza);
    }

    @Override
    public List<Pizza> readAll() {
        return em.createNamedQuery("Pizza.getAll", Pizza.class).getResultList();
    }

    @Override
    @Transactional
    public Long create(Pizza pizza) {
        em.persist(pizza);
        em.flush();
        return pizza.getId();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(id == null || id <= 0) {
            return;
        }
        em.createNamedQuery("Pizza.deleteById").setParameter("id", id).executeUpdate();
    }
    
}