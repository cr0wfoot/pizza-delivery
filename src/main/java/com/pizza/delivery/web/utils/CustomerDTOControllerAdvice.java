package com.pizza.delivery.web.utils;

import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.User;
import com.pizza.delivery.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Controller advice used to provide CustomerDTO in model
 * @see UserService
 * @see CustomerDTO
 */
@ControllerAdvice
public class CustomerDTOControllerAdvice {
    
    @Autowired
    private UserService userService;
    
    /**
     * Provide CustomerDTO in model
     * @param principal
     * @return 
     */
    @ModelAttribute("customerDto")
    public CustomerDTO getDto(Principal principal) {
        CustomerDTO customerDto = new CustomerDTO();
        if(principal != null) {
            User user = userService.findByLogin(principal.getName(), false);
            customerDto.buildDTOWithUser(user);
        }
        return customerDto;
    }
    
}