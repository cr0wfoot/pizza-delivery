package com.pizza.delivery.service.simple;

import com.pizza.delivery.domain.entities.Pizza;
import com.pizza.delivery.domain.entities.Pizza.PizzaType;
import com.pizza.delivery.repository.PizzaRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test of Class SimplePizzaService
 * @see SimplePizzaService
 */
@RunWith(MockitoJUnitRunner.class)
public class SimplePizzaServiceTest {
    
    @Mock
    private PizzaRepository repository;
    
    @InjectMocks
    private SimplePizzaService instance;
    
    public SimplePizzaServiceTest() {
    }

    /**
     * Get default list of pizzas with three initialized pizzas
     * @return list of pizzas 
     */
    private List<Pizza> getDefaultListOfPizzas() {
        List<Pizza> pizzas = new ArrayList<Pizza>();
        pizzas.add(new Pizza(1l, "1", 1.0, PizzaType.MEAT));
        pizzas.add(new Pizza(2l, "2", 2.0, PizzaType.MEAT));
        pizzas.add(new Pizza(3l, "3", 3.0, PizzaType.MEAT));
        return pizzas;
    }
    
    /**
     * Get default initialized Pizza object
     * @return pizza 
     */
    private Pizza getDefaultPizza() {
        return new Pizza(1l, "1", 1.0, PizzaType.MEAT);
    }
    
    /**
     * Test of find method, of class SimplePizzaService.
     */
    @Test
    public void testFind_ExistingPizza_ShouldReturnDefaultPizza() {
        Long inputId = getDefaultPizza().getId();
        Pizza expectedResult = getDefaultPizza();
        
        when(repository.read(inputId)).thenReturn(expectedResult);
        Pizza result = instance.find(inputId);
        
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of find method, of class SimplePizzaService.
     */
    @Test
    public void testFind_PizzaWhichNotExist_ShouldReturnNull() {
        Long inputId = getDefaultPizza().getId();
        Pizza expectedResult = null;
        
        when(repository.read(inputId)).thenReturn(null);
        Pizza result = instance.find(inputId);
        
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of find method, of class SimplePizzaService.
     */
    @Test
    public void testFind_Null_ShouldReturnNull() {
        Long inputId = null;
        Pizza expectedResult = null;
        
        when(repository.read(inputId)).thenReturn(null);
        Pizza result = instance.find(inputId);
        
        assertEquals(expectedResult, result);
    }

    /**
     * Test of save method, of class SimplePizzaService.
     */
    @Test
    public void testSave_PizzaWithId_UpdateMethodOfRepositoryShouldBeInvoked() {        
        Pizza pizzaWithId = getDefaultPizza();
        
        instance.save(pizzaWithId);
        
        verify(repository).update(pizzaWithId);
    }
    
    /**
     * Test of save method, of class SimplePizzaService.
     */
    @Test
    public void testSave_PizzaWithoutId_CreateMethodOfRepositoryShouldBeInvoked() {        
        Pizza pizzaWithoutId = getDefaultPizza();
        pizzaWithoutId.setId(null);
        
        instance.save(pizzaWithoutId);
        
        verify(repository).create(pizzaWithoutId);
    }

    /**
     * Test of findAll method, of class SimplePizzaService.
     */
    @Test
    public void testFindAll_RepositoryWithPizzas_ShouldReturnDefaultListOfPizzas() {
        List<Pizza> expectedResult = getDefaultListOfPizzas();
        
        when(repository.readAll()).thenReturn(expectedResult);
        List result = instance.findAll();
        
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of findAll method, of class SimplePizzaService.
     */
    @Test
    public void testFindAll_RepositoryWithoutPizzas_ShouldReturnEmptyList() {
        List<Pizza> expectedResult = new ArrayList();
        
        when(repository.readAll()).thenReturn(new ArrayList());
        List result = instance.findAll();
        
        assertEquals(expectedResult, result);
    }

    /**
     * Test of delete method, of class SimplePizzaService.
     */
    @Test
    public void testDelete_Null_DeleteMethodOfRepositoryShouldBeInvoked() {
        Long inputId = null;
        
        instance.delete(inputId);
        
        verify(repository).delete(inputId);
    }
    
    /**
     * Test of delete method, of class SimplePizzaService.
     */
    @Test
    public void testDelete_DefaultPizzaId_DeleteMethodOfRepositoryShouldBeInvoked() {
        Long inputId = getDefaultPizza().getId();
        
        instance.delete(inputId);
        
        verify(repository).delete(inputId);
    }
    
}