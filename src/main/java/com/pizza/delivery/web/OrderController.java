package com.pizza.delivery.web;

import com.pizza.delivery.domain.Basket;
import com.pizza.delivery.domain.OrderState;
import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.Pizza;
import com.pizza.delivery.domain.entities.PizzaOrder;
import com.pizza.delivery.service.OrderService;
import com.pizza.delivery.service.UserService;
import java.util.Arrays;
import java.util.List;
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
    
    private List<OrderState> getOrderStates() {
        return Arrays.asList(OrderState.values());
    }
    
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("orders", orderService.showOrders());
        return "orders";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("customerDto") CustomerDTO dto, @ModelAttribute Basket basket) {
        orderService.placeNewOrder(dto, basket);
        return "redirect:/pizza/pizzas";
    }
    
    @RequestMapping(value = "/newstate", method = RequestMethod.POST)
    public String changeState(@RequestParam(value = "state", required = false) OrderState state, 
                              @RequestParam(value = "id", required = true) Long id) {
        orderService.changeStateOfOrder(id, state);
        return "redirect:/order/" + id;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.showOrder(id, false));
        model.addAttribute("orderStates", getOrderStates());
        return "order";
    }
    
}