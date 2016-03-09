package com.pizza.delivery.domain.discounts;

import com.pizza.delivery.domain.entities.*;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test of Class DiscountManager
 * @see DiscountManager
 * @see Discount
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/discountContext.xml"})
public class DiscountManagerTest {
        
    @Autowired
    private DiscountManager discountManager;
            
    public DiscountManagerTest() {
    }
    
    /**
     * Fills order with pizzas, where each next pizza has params according to pizza number
     * @param numberOfPizzas number of pizzas to create
     */
    private Set<OrderDetails> createListOfOrderDetails(int numberOfPizzas) {
        Integer defaultQuantityOfEachPizza = 2;
        Pizza.PizzaType defaultPizzaType = Pizza.PizzaType.MEAT;
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
     * Creates customer with discount card
     * @param pointsOnCard points on card
     */
    private Customer createCustomerWithCard(int pointsOnCard) {
        Customer customer = new Customer();
        DiscountCard card = new DiscountCard(); 
        card.setPoints(pointsOnCard);
        customer.setCard(card);
        return customer;
    }
    
    /**
     * Test of calculateTotalDiscount method, of class DiscountManager
     */
    @Test
    public void testCalculateTotalDiscount_Null_ShouldBe0() {
        PizzaOrder order = null;
        Double expectedResult = 0.;
        
        Double result = discountManager.calculateTotalDiscount(order);
        
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of calculateTotalDiscount method, of class DiscountManager
     */
    @Test
    public void testCalculateTotalDiscount_EmptyOrder_ShouldBe0() {
        PizzaOrder order = new PizzaOrder();
        Double expectedResult = 0.;
        
        Double result = discountManager.calculateTotalDiscount(order);
        
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of calculateTotalDiscount method, of class DiscountManager
     */
    @Test
    public void testCalculateTotalDiscount_OrderAcceptsDiscountForTheBiggestPizza_DiscountShouldBe3() {
        PizzaOrder order = new PizzaOrder();
        order.setDetails(createListOfOrderDetails(5));
        Double expectedResult = 3.;
        
        Double result = discountManager.calculateTotalDiscount(order);
        
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of calculateTotalDiscount method, of class DiscountManager
     */
    @Test
    public void testCalculateTotalDiscount_OrderAcceptsDiscountForDiscountCard_ShouldBe1() {
        PizzaOrder order = new PizzaOrder();
        order.setDetails(createListOfOrderDetails(3));
        order.setCustomer(createCustomerWithCard(10));
        Double expectedResult = 1.;
        
        Double result = discountManager.calculateTotalDiscount(order);
        
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of calculateTotalDiscount method, of class DiscountManager
     */
    @Test
    public void testCalculateTotalDiscount_OrderAcceptsAllDiscounts_ShouldBe4() {
        PizzaOrder order = new PizzaOrder();
        order.setDetails(createListOfOrderDetails(5));
        order.setCustomer(createCustomerWithCard(10));
        Double expectedResult = 4.;
        
        Double result = discountManager.calculateTotalDiscount(order);
        
        assertEquals(expectedResult, result);
    }
    
}