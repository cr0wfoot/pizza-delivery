package com.pizza.delivery.domain.validation;

import com.pizza.delivery.service.UserService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Custom validation: user's login must be unique.
 * @see UniqueLogin
 * @see CustomerDTO
 * @see UserService
 */
public class UserLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueLogin constraintAnnotation) {
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        return userService.findByLogin(login) == null;
    }

}