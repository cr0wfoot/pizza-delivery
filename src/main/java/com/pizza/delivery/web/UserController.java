package com.pizza.delivery.web;

import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.User;
import com.pizza.delivery.service.UserService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Web controller for handling requests related with user
 * @see UserService
 */
@Controller
@RequestMapping(value="/user/")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * Provides user in model
     * @param principal
     * @return 
     */
    @ModelAttribute()
    public User getUser(Principal principal) {
        if (principal == null) {
            return new User();
        }
        return userService.findByLogin(principal.getName(), false);
    }
    
    /**
     * Handles requests to registration page
     * @return 
     */
    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String registration() {
        return "userform";
    }
    
    /**
     * Handles requests for registrating new user or updating current one
     * @param customerDto an object of class CustomerDTO
     * @param result
     * @param attr
     * @param submit
     * @return 
     */
    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("customerDto") CustomerDTO customerDto, 
                            BindingResult result, 
                            RedirectAttributes attr, 
                            @RequestParam(value = "reg", required = false) String submit) {
        if (result.hasErrors()) {
            return "userform";
        }
        attr.addFlashAttribute("success", true);
        if(submit != null) {
            userService.registerNewUser(customerDto);
            return "redirect:/pizza/pizzas";
        } else {
            userService.updateUserInfo(customerDto);
            return "redirect:profile";
        } 
    }
    
    /**
     * Handles requests to user form page
     * @param model
     * @param customerDto an object of class CustomerDTO
     * @return 
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model, @ModelAttribute("customerDto") CustomerDTO customerDto) {
        return "userform";
    }

    /**
     * Handles requests to user info page
     * @param user an object of class User
     * @return 
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showProfilePage(@ModelAttribute User user) {
        return "profile";
    }

}