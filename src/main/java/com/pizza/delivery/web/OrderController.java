package com.pizza.delivery.web;

import com.pizza.delivery.domain.Basket;
import com.pizza.delivery.domain.OrderState;
import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.service.OrderService;
import com.pizza.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes(types = Basket.class)
@RequestMapping(value = "/order/")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("orders", orderService.showOrders());
        return "orders";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("customerDto") CustomerDTO dto, @ModelAttribute Basket basket) {
        orderService.placeNewOrder(dto, basket);
        return "redirect:pizza/pizzas";
    }
    
    @RequestMapping(value = "/newstate", method = RequestMethod.GET)
    public String changeState(@RequestParam(value = "state", required = false) OrderState state, 
                              @RequestParam(value = "id", required = false) Long id) {
        orderService.changeStateOfOrder(id, state);
        return "redirect:orders";
    }
    
}