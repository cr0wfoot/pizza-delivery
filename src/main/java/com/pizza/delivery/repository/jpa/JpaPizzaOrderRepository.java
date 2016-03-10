package com.pizza.delivery.repository.jpa;

import com.pizza.delivery.domain.entities.PizzaOrder;
import com.pizza.delivery.repository.PizzaOrderRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JpaPizzaOrderRepository implements PizzaOrderRepository {
    
    private static final boolean FETCH_TYPE_LAZY = true;

    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional
    public Long create(PizzaOrder order) {
        em.persist(order);
        em.flush();
        return order.getId();
    }
    
    @Override
    @Transactional(readOnly = true)
    public PizzaOrder read(Long id) {
        return readHelper(id, FETCH_TYPE_LAZY);
    }
    
    @Override
    @Transactional(readOnly = true)
    public PizzaOrder read(Long id, boolean fetchLazy) {
        return readHelper(id, fetchLazy);
    }
    
    private PizzaOrder readHelper(Long id, boolean fetchLazy) {
        if(id == null || id <= 0) { return null; }
        if(fetchLazy) {
            return em.find(PizzaOrder.class, id);
        } else {
            return em.createNamedQuery("PizzaOrder.getByIdFetchEager", PizzaOrder.class).setParameter("id", id).getSingleResult();
        }
    }

    @Override
    @Transactional
    public void update(PizzaOrder order) {
        if(order.getId() <= 0) {
            return;
        }
        em.merge(order);
    }

    @Override
    public List<PizzaOrder> readAll() {
        return em.createNamedQuery("PizzaOrder.getAll", PizzaOrder.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(id == null || id <= 0) {
            return;
        }
        em.createNamedQuery("PizzaOrder.deleteById").setParameter("id", id).executeUpdate();
    }
    
}