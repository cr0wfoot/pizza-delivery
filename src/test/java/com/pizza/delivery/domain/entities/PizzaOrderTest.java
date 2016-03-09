package com.pizza.delivery.domain.entities;

import com.pizza.delivery.domain.entities.Pizza.PizzaType;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of Class PizzaOrder
 * @see PizzaOrder
 */
public class PizzaOrderTest {
    
    private PizzaOrder order;
    
    public PizzaOrderTest() {
    }
    
    @Before
    public void setUp() {
        order = new PizzaOrder();
    }
    
    @After
    public void tearDown() {
        order = null;
    }

    /**
     * Fills order with pizzas, where each next pizza has params according to pizza number
     * @param numberOfPizzas number of pizzas to create
     */
    private Set<OrderDetails> createListOfOrderDetails(int numberOfPizzas) {
        Integer defaultQuantityOfEachPizza = 2;
        PizzaType defaultPizzaType = PizzaType.MEAT;
        Set<OrderDetails> details = new HashSet<OrderDetails>();
        for(Integer param = 1; param <= numberOfPizzas; param++) {
            details.add(new OrderDetails(
                                null, 
                                new Pizza(
                                    param.longValue(), 
                                    param.toString(), 
                                    param.doubleValue(), 
                                    defaultPizzaType), 
                                param.doubleValue(), 
                                defaultQuantityOfEachPizza)
                        );
        }
        return details;
    }

    /**
     * Test of getTotalPrice method, of class PizzaOrder, get total price of empty order
     */
    @Test
    public void testGetTotalPrice_OrderWithoutPizzas_TotalPriceShouldBe0() {
        Double expectedPrice = 0.0;
        
        Double resultPrice = order.getTotalPrice();
        
        assertEquals(expectedPrice, resultPrice);
    }
    
    /**
     * Test of getTotalPrice method, of class PizzaOrder, get total price of order with pizzas
     */
    @Test
    public void testGetTotalPrice_OrderWith3Pizzas_TotalPriceShouldBe12() {
        order.setDetails(createListOfOrderDetails(3));
        Double expectedPrice = 12.0;
        
        Double resultPrice = order.getTotalPrice();
        
        assertEquals(expectedPrice, resultPrice);
    }
    
}