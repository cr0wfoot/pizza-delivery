package com.pizza.delivery.repository;

import com.pizza.delivery.domain.entities.User;
import java.util.List;

/**
 * Contains operations to interact with part of database which is responsible for user
 * @see User
 */
public interface UserRepository {
    
    /**
     * Creates user in database, returns id of created user
     * @param customer an object of class User
     * @return id of created user
     */
    Long create(User user);
    
    /**
     * Get user from database by id
     * @param id the value of id
     * @return an object of class User
     */
    User read(Long id);
    
    /**
     * Get user from database by login
     * @param login the value of login
     * @return an object of class User
     */
    User findByLogin(String login);
    
    /**
     * Overload, user customer from database by id with possibility to load all fetch fields of user
     * @param login the value of login
     * @param fetchLazy true - fetch lazy, false - fetch eager.
     * @return an object of class User
     */
    User findByLogin(String login, boolean fetchEager);
    
    /**
     * Updates user in database
     * @param customer an object of class User
     */
    void update(User user);
    
    /**
     * Get all users from database
     * @return the list of users
     */
    List<User> readAll();
    
    /**
     * Remove user from database by user's id
     * @param id the value of id
     */
    void delete(Long id);

}