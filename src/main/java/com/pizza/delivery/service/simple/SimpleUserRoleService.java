package com.pizza.delivery.service.simple;

import com.pizza.delivery.domain.entities.UserRole;
import com.pizza.delivery.repository.UserRoleRepository;
import com.pizza.delivery.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Simple implementation, interacts with user role layer
 * @see UserRole
 * @see UserRoleRepository
 */
@Service
public class SimpleUserRoleService implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Override
    public UserRole findByAuthority(String authority) {
        return userRoleRepository.findByAuthority(authority);
    }
    
}