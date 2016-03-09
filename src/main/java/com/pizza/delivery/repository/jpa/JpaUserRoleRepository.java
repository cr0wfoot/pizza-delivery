package com.pizza.delivery.repository.jpa;

import com.pizza.delivery.domain.entities.UserRole;
import com.pizza.delivery.repository.UserRoleRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserRoleRepository implements UserRoleRepository {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public UserRole findByAuthority(String authority) {
        if(authority == null || authority.isEmpty()) {
            return null;
        }
        List<UserRole> list = em.createNamedQuery("UserRole.getByAuthority", UserRole.class).setParameter("authority", authority).getResultList();
        if(list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
    
}