package com.pizza.delivery.repository;

import com.pizza.delivery.domain.entities.UserRole;

/**
 * Contains operations to interact with part of database which is responsible for user role
 * @see UserRole
 */
public interface UserRoleRepository {
    
    /**
     * Get user role from database by authority
     * @param authority the string value of authority
     * @return an object of class UserRole
     */
    UserRole findByAuthority(String authority);
    
}