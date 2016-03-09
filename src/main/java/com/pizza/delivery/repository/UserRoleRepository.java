package com.pizza.delivery.repository;

import com.pizza.delivery.domain.entities.UserRole;

public interface UserRoleRepository {
    
    UserRole findByAuthority(String authority);
    
}