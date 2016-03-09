package com.pizza.delivery.web.utils;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionControllerAdvice {
    
    private final static Logger logger = Logger.getLogger(ExceptionControllerAdvice.class);
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String error(Exception e, Model model) {
        logger.error(e);
        model.addAttribute("ex", e);
        return "error";
    }
    
}