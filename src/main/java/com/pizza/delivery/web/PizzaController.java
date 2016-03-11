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

@Controller
@SessionAttributes(types = Basket.class)
@RequestMapping(value = "/pizza/")
public class PizzaController {
    
    @Autowired
    private PizzaService pizzaService;
    
    @ModelAttribute("pizza")
    public Pizza findPizza(@RequestParam(value = "id", required = false) Pizza pizza) {
        if(pizza == null) return new Pizza();
        return pizza;
    }
    
    @Lookup
    @ModelAttribute
    Basket createBasket() { return null; }
    
    private List<PizzaType> getPizzaTypes() {
        return Arrays.asList(PizzaType.values());
    }
    
    @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("pizzas", pizzaService.findAll());
        return "pizzas";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String findById(@PathVariable Long id) {
        return pizzaService.find(id).toString();
    }
    
    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String create(Model model, Pizza pizza) {
        model.addAttribute("pizzaTypes", getPizzaTypes());
        return "pizzaform";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult result,  Model model, RedirectAttributes attr) {
        if (result.hasErrors()) {
            model.addAttribute("pizzaTypes", getPizzaTypes());
            return "pizzaform";
        }
        attr.addFlashAttribute("success", true);
        pizzaService.save(pizza);
        return "redirect:pizzas";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Model model, Pizza pizza) {
        model.addAttribute("pizza", pizza);
        model.addAttribute("pizzaTypes", getPizzaTypes());
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