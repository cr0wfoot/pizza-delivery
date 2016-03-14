package com.pizza.delivery.service;

import com.pizza.delivery.domain.entities.UserRole;

/**
 * Interacts with part of repository's layer which is responsible for user role.
 * @see UserRole
 */
public interface UserRoleService {
    
    /**
     * Find user role by given authority
     * @param id the value of authority
     * @return an object of class UserRole
     */
    UserRole findByAuthority(String authority);
    
}