package com.pizza.delivery.service;

import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.User;

/**
 * Interacts with part of repository's layer which is responsible for user.
 * @see UserService
 * @see CustomerDTO
 */
public interface UserService {
    
    /**
     * Find customer by given login
     * @param id the value of login
     * @return an object of class User
     */
    User findByLogin(String login);
    
    /**
     * Find customer by given login with possibility to load all fetch fields of User
     * @param id the value of login
     * @param fetchLazy true - fetch lazy, false - fetch eager.
     * @return an object of class User
     */
    User findByLogin(String login, boolean fetchLazy);
    
    /**
     * Register new user with information from CustomerDTO
     * @param customerDto an object of class CustomerDTO
     */
    void registerNewUser(CustomerDTO customerDto);
    
    /**
     * Update user with information from CustomerDTO
     * @param customerDto an object of class CustomerDTO
     */
    void updateUserInfo(CustomerDTO customerDto);
        
}