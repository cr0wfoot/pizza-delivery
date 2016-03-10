package com.pizza.delivery.repository;

import com.pizza.delivery.domain.entities.User;
import java.util.List;

public interface UserRepository {
    
    Long create(User user);
    
    User read(Long id);
    
    User findByLogin(String login);
    
    User findByLogin(String login, boolean fetchEager);
    
    void update(User user);
    
    List<User> readAll();
    
    void delete(Long id);

}