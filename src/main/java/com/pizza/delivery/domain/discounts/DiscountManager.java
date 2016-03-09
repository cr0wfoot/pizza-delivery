package com.pizza.delivery.domain.discounts;

import com.pizza.delivery.domain.entities.PizzaOrder;
import java.util.List;

public class DiscountManager {
    
    private List<Discount> discounts;

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }
    
    public DiscountManager() {
    }
        
    public Double calculateTotalDiscount(PizzaOrder order) {
        if(order == null) {
            return 0.;
        }
        Double totalDiscount = 0.;
        for(Discount d : discounts) {
            totalDiscount += d.calculate(order);
        }
        return totalDiscount;
    }
        
}