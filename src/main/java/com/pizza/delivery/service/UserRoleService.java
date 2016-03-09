package com.pizza.delivery.service;

import com.pizza.delivery.domain.entities.UserRole;

public interface UserRoleService {
    
    UserRole findByAuthority(String authority);
    
}