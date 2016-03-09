package com.pizza.delivery.repository.jpa;

import com.pizza.delivery.domain.entities.Customer;
import com.pizza.delivery.repository.CustomerRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JpaCustomerRepository implements CustomerRepository {

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
        if(id == null || id <= 0) {
            return null;
        }
        Customer customer = em.find(Customer.class, id);
        String.valueOf(customer.getAddress());
        String.valueOf(customer.getCard());
        String.valueOf(customer.getOrders());
        return customer;
    }
    
    @Override
    @Transactional
    public void update(Customer customer) {
        if(customer == null){
            return;
        }
        em.merge(customer);
    }

    @Override
    public List<Customer> readAll() {
        return em.createNamedQuery("Customer.getAll", Customer.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(id == null || id <= 0) {
            return;
        }
        em.remove(em.find(Customer.class, id));
    }
    
}