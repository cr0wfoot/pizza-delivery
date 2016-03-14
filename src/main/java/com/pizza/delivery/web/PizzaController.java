package com.pizza.delivery.web;

import com.pizza.delivery.domain.Basket;
import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.Pizza;
import com.pizza.delivery.domain.entities.Pizza.PizzaType;
import com.pizza.delivery.service.PizzaService;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Web controller for handling requests related with pizza
 * @see PizzaService
 */
@Controller
@SessionAttributes(types = Basket.class)
@RequestMapping(value = "/pizza/")
public class PizzaController {
    
    @Autowired
    private PizzaService pizzaService;
    
    /**
     * Provides pizza in model
     * @param pizza an object of class Pizza
     * @return an object of class Pizza
     */
    @ModelAttribute("pizza")
    public Pizza findPizza(@RequestParam(value = "id", required = false) Pizza pizza) {
        if(pizza == null) return new Pizza();
        return pizza;
    }
    
    /**
     * Provides basket in model
     * @return an object of class Basket
     */
    @Lookup
    @ModelAttribute
    Basket createBasket() { return null; }
    
    private List<PizzaType> getPizzaTypes() {
        return Arrays.asList(PizzaType.values());
    }
    
    /**
     * Handles the requests to pizzas' main page
     * @param model
     * @return 
     */
    @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("pizzas", pizzaService.findAll());
        return "pizzas";
    }
    
    /**
     * Handles the requests to single pizza info page
     * @param id id of pizza
     * @return 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String findById(@PathVariable Long id) {
        return pizzaService.find(id).toString();
    }
    
    /**
     * Handles the requests to pizza form page
     * @param model
     * @param pizza an object of class Pizza
     * @return 
     */
    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String create(Model model, Pizza pizza) {
        model.addAttribute("pizzaTypes", getPizzaTypes());
        return "pizzaform";
    }
    
    /**
     * Handles the requests for creating or updating pizza
     * @param pizza an object of class Pizza
     * @param result
     * @param model
     * @param attr
     * @return 
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult result,  Model model, RedirectAttributes attr) {
        if (result.hasErrors()) {
            model.addAttribute("pizzaTypes", getPizzaTypes());
            return "pizzaform";
        }
        attr.addFlashAttribute("success", true);
        pizzaService.save(pizza);
        return "redirect:pizzas";
    }
    
    /**
     * Handles the requests to pizza form page
     * @param model
     * @param pizza an object of class Pizza
     * @return 
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Model model, Pizza pizza) {
        model.addAttribute("pizza", pizza);
        model.addAttribute("pizzaTypes", getPizzaTypes());
        return "pizzaform";
    }
    
    /**
     * Handles the requests for removing pizza
     * @param pizza an object of class Pizza
     * @return 
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(Pizza pizza) {
        pizzaService.delete(pizza.getId());
        return "redirect:pizzas";
    }
    
    /**
     * Handles the requests for adding pizza to basket
     * @param pizza an object of class Pizza
     * @param basket an object of class Basket
     * @return 
     */
    @RequestMapping(value = "/basket-add", method = RequestMethod.POST)
    public String addPizzaToBasket(Pizza pizza, @ModelAttribute Basket basket) {
        basket.add(pizza);
        return "redirect:pizzas";
    }

    /**
     * Handles the requests for removing pizza from basket
     * @param pizza an object of class Pizza
     * @param basket an object of class Basket
     * @return 
     */
    @RequestMapping(value = "/basket-del", method = RequestMethod.POST)
    public String removePizzaFromBasket(Pizza pizza, @ModelAttribute Basket basket) {
        basket.remove(pizza);
        return "redirect:basket";
    }
    
    /**
     * Handles the requests to basket page
     * @param customerDto an object of class CustomerDTO
     * @return 
     */
    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String goToBasket(@ModelAttribute("customerDto") CustomerDTO customerDto) {
        return "basket";
    }
    
}