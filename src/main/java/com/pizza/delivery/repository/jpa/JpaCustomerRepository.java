package com.pizza.delivery.repository.jpa;

import com.pizza.delivery.domain.entities.Customer;
import com.pizza.delivery.repository.CustomerRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation, interacts with Customer
 * @see Customer
 */
@Repository
public class JpaCustomerRepository implements CustomerRepository {
    
    private static final boolean FETCH_TYPE_LAZY = true;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Long create(Customer customer) {
        em.persist(customer);
        em.flush();
        return customer.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Customer read(Long id) {
        return readHelper(id, FETCH_TYPE_LAZY);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Customer read(Long id, boolean fetchLazy) {
        return readHelper(id, fetchLazy);
    }
    
    private Customer readHelper(Long id, boolean fetchLazy) {
        if(id == null || id <= 0) { return null; }
        if(fetchLazy) {
            return em.find(Customer.class, id);
        } else {
            return em.createNamedQuery("Customer.getByIdFetchEager", Customer.class).setParameter("id", id).getSingleResult();
        }
    }
    
    @Override
    @Transactional
    public void update(Customer customer) {
        if(customer == null){ return; }
        em.merge(customer);
    }

    @Override
    public List<Customer> readAll() {
        return em.createNamedQuery("Customer.getAll", Customer.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(id == null || id <= 0) { return; }
        em.remove(em.find(Customer.class, id));
    }
    
}