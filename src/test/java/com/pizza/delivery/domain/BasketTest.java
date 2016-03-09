package com.pizza.delivery.domain;

import com.pizza.delivery.domain.entities.Pizza;
import com.pizza.delivery.domain.entities.Pizza.PizzaType;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of Class Basket
 * @see Basket
 */
public class BasketTest {
    
    private Basket basket;
    
    public BasketTest() {
    }
    
    @Before
    public void setUp() {
        basket = new Basket();
    }
    
    @After
    public void tearDown() {
        basket = null;
    }
    
    private void initBasketWithPizzas(int numberOfPizzas, int defaultQuantityOfEachPizza) {
        PizzaType defaultPizzaType = PizzaType.MEAT;
        for(Integer param = 1; param <= numberOfPizzas; param++) {
            basket.getPizzas().put(
                    new Pizza(
                          param.longValue(), 
                          param.toString(), 
                          param.doubleValue(), 
                          defaultPizzaType), 
                    defaultQuantityOfEachPizza
                );
        } 
    }

    /**
     * Test of getPrice method, of class Basket.
     */
    @Test
    public void testGetPrice_EmptyBasket_ShouldBe0() {
        Double expectedPrice = 0.0;
        
        Double result = basket.getPrice();
        
        assertEquals(expectedPrice, result);
    }
    
    /**
     * Test of getPrice method, of class Basket.
     */
    @Test
    public void testGetPrice_BasketWith3Pizzas_ShouldBe12() {
        Double expectedPrice = 12.0;
        initBasketWithPizzas(3, 2);
        
        Double result = basket.getPrice();
        
        assertEquals(expectedPrice, result);
    }

    /**
     * Test of getQuantity method, of class Basket.
     */
    @Test
    public void testGetQuantity_EmptyBasket_ShouldBe0() {
        int expectedQuantity = 0;
        
        int result = basket.getQuantity();
        
        assertEquals(expectedQuantity, result);
    }
    
    /**
     * Test of getQuantity method, of class Basket.
     */
    @Test
    public void testGetQuantity_EmptyBasket_ShouldBe6() {
        int expectedQuantity = 6;
        initBasketWithPizzas(3, 2);
        
        int result = basket.getQuantity();
        
        assertEquals(expectedQuantity, result);
    }

    /**
     * Test of add method, of class Basket.
     */
    @Test
    public void testAdd_SamePizzaAsFirst_QuantityOfFirstPizzaShouldBe2() {
        int expectedQuantityOfFirstPizza = 3;
        Pizza pizzaToAdd = new Pizza(1l, "1", 1.0, PizzaType.MEAT);
        initBasketWithPizzas(3, 2);
        
        basket.add(pizzaToAdd);
        int result = basket.getPizzas().get(pizzaToAdd);
        
        assertEquals(expectedQuantityOfFirstPizza, result);
    }
    
    /**
     * Test of add method, of class Basket.
     */
    @Test
    public void testAdd_NewPizza_QuantityOfAddedPizzaShouldBe1() {
        int expectedQuantityOfAddedPizza = 1;
        Pizza pizzaToAdd = new Pizza(4l, "4", 4.0, PizzaType.MEAT);
        initBasketWithPizzas(3, 2);
        
        basket.add(pizzaToAdd);
        int result = basket.getPizzas().get(pizzaToAdd);
        
        assertEquals(expectedQuantityOfAddedPizza, result);
    }
    
    /**
     * Test of add method, of class Basket.
     */
    @Test
    public void testAdd_Null_QuantityOfPizzasShouldBe3() {
        int expectedQuantity = 6;
        Pizza pizzaToAdd = null;
        initBasketWithPizzas(3, 2);
        
        basket.add(pizzaToAdd);
        int result = basket.getQuantity();
        
        assertEquals(expectedQuantity, result);
    }

    /**
     * Test of remove method, of class Basket.
     */
    @Test
    public void testRemove_Null_QuantityOfPizzasShouldBe3() {
        int expectedQuantity = 6;
        Pizza pizzaToRemove = null;
        initBasketWithPizzas(3, 2);
        
        basket.remove(pizzaToRemove);
        int result = basket.getQuantity();
        
        assertEquals(expectedQuantity, result);
    }
    
    /**
     * Test of remove method, of class Basket.
     */
    @Test
    public void testRemove_ExistingPizzaOneOfTwoSame_QuantityOfPizzasShouldBe5() {
        int expectedQuantity = 5;
        Pizza pizzaToRemove = new Pizza(1l, "1", 1.0, PizzaType.MEAT);
        initBasketWithPizzas(3, 2);
        
        basket.remove(pizzaToRemove);
        int result = basket.getQuantity();
        
        assertEquals(expectedQuantity, result);
    }
    
    /**
     * Test of remove method, of class Basket.
     */
    @Test
    public void testRemove_PizzaNotExist_QuantityOfPizzasShouldBe6() {
        int expectedQuantity = 6;
        Pizza pizzaToRemove = new Pizza(4l, "4", 4.0, PizzaType.MEAT);
        initBasketWithPizzas(3, 2);
        
        basket.remove(pizzaToRemove);
        int result = basket.getQuantity();
        
        assertEquals(expectedQuantity, result);
    }
    
}