package com.pizza.delivery.repository.jpa;

import com.pizza.delivery.domain.entities.User;
import com.pizza.delivery.repository.UserRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation, interacts with User
 * @see User
 */
@Repository
public class JpaUserRepository implements UserRepository {
    
    private static final boolean FETCH_TYPE_LAZY = true;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Long create(User user) {
        em.merge(user);
        em.flush();
        return user.getId();
    }

    @Override
    public User read(Long id) {
        if(id == null || id <= 0) { return null; }
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public void update(User user) {
        if(user == null || user.getId() <= 0) { return; }
        em.merge(user);
    }

    @Override
    public List<User> readAll() {
        return em.createNamedQuery("User.getAll", User.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(id == null || id <= 0) {
            return;
        }
        em.createNamedQuery("User.deleteById", User.class).executeUpdate();
    }

    @Override
    public User findByLogin(String login) {
        return findByLoginHelper(login, FETCH_TYPE_LAZY);
    }
    
    @Override
    public User findByLogin(String login, boolean fetchLazy) {
        return findByLoginHelper(login, fetchLazy);
    }
    
    private User findByLoginHelper(String login, boolean fetchLazy) {
        if(login == null || login.isEmpty()) { return null; }
        String query = "User.getByLoginFetchEager";
        if(fetchLazy) {
            query = "User.getByLogin";
        }
        try {
            return em.createNamedQuery(query, User.class).setParameter("login", login).getSingleResult();
        } catch(NoResultException ex) {
            return null;
        }
    }
    
}