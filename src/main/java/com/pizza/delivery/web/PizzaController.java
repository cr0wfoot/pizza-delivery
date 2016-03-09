package com.pizza.delivery.web;

import com.pizza.delivery.domain.Basket;
import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.Pizza;
import com.pizza.delivery.service.PizzaService;
import com.pizza.delivery.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes(types = Basket.class)
@RequestMapping(value = "/pizza/")
public class PizzaController {
    
    @Autowired
    private PizzaService pizzaService;
    
    @Autowired
    private UserService userService;
    
    @ModelAttribute("pizza")
    public Pizza findPizza(@RequestParam(value = "id", required = false) Pizza pizza) {
        return pizza;
    }
    
    @ModelAttribute
    public Basket createBasket() {
        return new Basket();
    }
    
    @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("pizzas", pizzaService.findAll());
        return "pizzas";
    }
    
    @RequestMapping(value = "/pizza", method = RequestMethod.GET)
    @ResponseBody
    public String findById(Pizza pizza) {
        return pizza.toString();
    }
    
    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String create() {
        return "pizzaform";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String add(@Valid Pizza pizza, Model model, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "userform";
        }
        attr.addFlashAttribute("success", true);
        pizzaService.save(pizza);
        return "redirect:pizzas";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Model model, Pizza pizza) {
        model.addAttribute("pizza", pizza);
        return "pizzaform";
    }
    
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(Pizza pizza) {
        pizzaService.delete(pizza.getId());
        return "redirect:pizzas";
    }
    
    @RequestMapping(value = "/basket-add", method = RequestMethod.POST)
    public String addPizzaToBasket(Pizza pizza, @ModelAttribute Basket basket) {
        basket.add(pizza);
        return "redirect:pizzas";
    }

    @RequestMapping(value = "/basket-del", method = RequestMethod.POST)
    public String removePizzaFromBasket(Pizza pizza, @ModelAttribute Basket basket) {
        basket.remove(pizza);
        return "redirect:basket";
    }
    
    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String goToBasket(@ModelAttribute("customerDto") CustomerDTO customerDto) {
        return "basket";
    }
    
}