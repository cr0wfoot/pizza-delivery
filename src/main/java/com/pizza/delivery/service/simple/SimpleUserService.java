package com.pizza.delivery.service.simple;

import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.Customer;
import com.pizza.delivery.domain.entities.User;
import com.pizza.delivery.domain.entities.UserRole;
import com.pizza.delivery.repository.UserRepository;
import com.pizza.delivery.service.CustomerService;
import com.pizza.delivery.service.UserRoleService;
import com.pizza.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Simple implementation, interacts with user layer, work with customer service, 
 * user role service, password encoder
 * @see User
 * @see UserRepository
 * @see UserRoleService
 * @see CustomerService
 * @see PasswordEncoder
 */
@Service
public class SimpleUserService implements UserService {

    private static final String DEFAULT_NEW_USER_ROLE = "ROLE_USER";
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
    
    @Override
    public User findByLogin(String login, boolean fetchLazy) {
        return userRepository.findByLogin(login, fetchLazy);
    }

    @Override
    @Transactional
    public void registerNewUser(CustomerDTO customerDto) {
        if(customerDto == null) return;
        if(userRepository.findByLogin(customerDto.getLogin()) != null) 
            throw new IllegalArgumentException();
        Customer customer = customerService.createNewCustomer(customerDto);
        User user = getUserFromDTO(customerDto);
        user = setDefaultUserRole(user);
        user.setCustomer(customer);
        userRepository.create(user);
    }

    @Override
    @Transactional
    public void updateUserInfo(CustomerDTO customerDto) {
        if(customerDto == null) return;
        User user = getUserFromDTO(customerDto);
        Customer customer = customerService.getCustomerFromDTO(customerDto);
        user.setCustomer(customer);
        customerService.update(customer);
        userRepository.update(user);
    }
    
    @Transactional(readOnly = true)
    private User getUserFromDTO(CustomerDTO customerDto) {
        if(customerDto == null) return new User();
        User user = null;
        if(customerDto.getUserId() != null) {
            user = userRepository.read(customerDto.getUserId());
        }
        if(user == null) {
            user = new User();
        }
        user.setLogin(customerDto.getLogin());
        user.setPassword(encoder.encode(customerDto.getPass()));
        return user;
    }
    
    private User setDefaultUserRole(User user) {
        UserRole defaultRole = userRoleService.findByAuthority(DEFAULT_NEW_USER_ROLE);
        user.getRoles().add(defaultRole);
        return user;
    }
    
}