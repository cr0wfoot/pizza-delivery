package com.pizza.delivery.service;

import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.User;

public interface UserService {
    
    User findByLogin(String login);
    
    User findByLogin(String login, boolean fetchLazy);
    
    void registerNewUser(CustomerDTO customerDto);
    
    void updateUserInfo(CustomerDTO customerDto);
        
}