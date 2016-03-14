package com.pizza.delivery.domain.discounts;

import com.pizza.delivery.domain.entities.PizzaOrder;
import java.util.List;

/**
 * Contains all discounts to calculate total discount.
 * @see Discount
 * @see PizzaOrder
 */
public class DiscountManager {
    
    private List<Discount> discounts;

    /**
     * Get all discounts
     * @return the list of discounts
     */
    public List<Discount> getDiscounts() {
        return discounts;
    }

    /**
     * Set the list of discounts
     * @param discounts the list of discounts
     */
    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }
    
    /**
     * An empty constructor
     */
    public DiscountManager() {
    }
    
    /**
     * Iterates the list of discounts and calculates total discount
     * @param order an object of class PizzaOrder
     * @return the double vlaue of total discount for order
     */
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