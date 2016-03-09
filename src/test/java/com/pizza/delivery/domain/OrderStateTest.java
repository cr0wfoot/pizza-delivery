package com.pizza.delivery.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test of OrderState
 * @see OrderState
 */
public class OrderStateTest {

    public OrderStateTest() {
    }

    /**
     * Test of isAvaliableStateToChange method, of class OrderState.
     */
    @Test
    public void testIsAvaliableStateToChange_ChangeStateToNull_ShouldBeFalse() {
        OrderState currentState = OrderState.NEW;
        OrderState stateToChange = null;
        
        boolean result = currentState.isAvaliableStateToChange(stateToChange);
        
        assertFalse(result);
    }
    
    /**
     * Test of isAvaliableStateToChange method, of class OrderState.
     */
    @Test
    public void testIsAvaliableStateToChange_ChangeNewToCanceled_ShouldBeTrue() {
        OrderState currentState = OrderState.NEW;
        OrderState stateToChange = OrderState.CANCELED;
        
        boolean result = currentState.isAvaliableStateToChange(stateToChange);
        
        assertTrue(result);
    }
    
    /**
     * Test of isAvaliableStateToChange method, of class OrderState.
     */
    @Test
    public void testIsAvaliableStateToChange_ChangeNewToInProgress_ShouldBeTrue() {
        OrderState currentState = OrderState.NEW;
        OrderState stateToChange = OrderState.IN_PROGRSS;
        
        boolean result = currentState.isAvaliableStateToChange(stateToChange);
        
        assertTrue(result);
    }
    
    /**
     * Test of isAvaliableStateToChange method, of class OrderState.
     */
    @Test
    public void testIsAvaliableStateToChange_ChangeNewToDone_ShouldBeFalse() {
        OrderState currentState = OrderState.NEW;
        OrderState stateToChange = OrderState.DONE;
        
        boolean result = currentState.isAvaliableStateToChange(stateToChange);
        
        assertFalse(result);
    }
    
    /**
     * Test of isAvaliableStateToChange method, of class OrderState.
     */
    @Test
    public void testIsAvaliableStateToChange_ChangeInProgresssToNew_ShouldBeFalse() {
        OrderState currentState = OrderState.IN_PROGRSS;
        OrderState stateToChange = OrderState.NEW;
        
        boolean result = currentState.isAvaliableStateToChange(stateToChange);
        
        assertFalse(result);
    }
    
    /**
     * Test of isAvaliableStateToChange method, of class OrderState.
     */
    @Test
    public void testIsAvaliableStateToChange_ChangeInProgresssToCanceled_ShouldBeTrue() {
        OrderState currentState = OrderState.IN_PROGRSS;
        OrderState stateToChange = OrderState.CANCELED;
        
        boolean result = currentState.isAvaliableStateToChange(stateToChange);
        
        assertTrue(result);
    }
    
    /**
     * Test of isAvaliableStateToChange method, of class OrderState.
     */
    @Test
    public void testIsAvaliableStateToChange_ChangeInProgresssToDone_ShouldBeTrue() {
        OrderState currentState = OrderState.IN_PROGRSS;
        OrderState stateToChange = OrderState.DONE;
        
        boolean result = currentState.isAvaliableStateToChange(stateToChange);
        
        assertTrue(result);
    }
    
    /**
     * Test of isAvaliableStateToChange method, of class OrderState.
     */
    @Test
    public void testIsAvaliableStateToChange_ChangeDoneToNew_ShouldBeFalse() {
        OrderState currentState = OrderState.DONE;
        OrderState stateToChange = OrderState.NEW;
        
        boolean result = currentState.isAvaliableStateToChange(stateToChange);
        
        assertFalse(result);
    }
    
    /**
     * Test of isAvaliableStateToChange method, of class OrderState.
     */
    @Test
    public void testIsAvaliableStateToChange_ChangeDoneToCanceled_ShouldBeFalse() {
        OrderState currentState = OrderState.DONE;
        OrderState stateToChange = OrderState.CANCELED;
        
        boolean result = currentState.isAvaliableStateToChange(stateToChange);
        
        assertFalse(result);
    }
    
    /**
     * Test of isAvaliableStateToChange method, of class OrderState.
     */
    @Test
    public void testIsAvaliableStateToChange_ChangeDoneToInProgress_ShouldBeFalse() {
        OrderState currentState = OrderState.DONE;
        OrderState stateToChange = OrderState.IN_PROGRSS;
        
        boolean result = currentState.isAvaliableStateToChange(stateToChange);
        
        assertFalse(result);
    }
    
    /**
     * Test of isAvaliableStateToChange method, of class OrderState.
     */
    @Test
    public void testIsAvaliableStateToChange_ChangeCanceledToInProgress_ShouldBeFalse() {
        OrderState currentState = OrderState.CANCELED;
        OrderState stateToChange = OrderState.IN_PROGRSS;
        
        boolean result = currentState.isAvaliableStateToChange(stateToChange);
        
        assertFalse(result);
    }
    
    /**
     * Test of isAvaliableStateToChange method, of class OrderState.
     */
    @Test
    public void testIsAvaliableStateToChange_ChangeCanceledToNew_ShouldBeFalse() {
        OrderState currentState = OrderState.CANCELED;
        OrderState stateToChange = OrderState.NEW;
        
        boolean result = currentState.isAvaliableStateToChange(stateToChange);
        
        assertFalse(result);
    }
    
    /**
     * Test of isAvaliableStateToChange method, of class OrderState.
     */
    @Test
    public void testIsAvaliableStateToChange_ChangeCanceledToDone_ShouldBeFalse() {
        OrderState currentState = OrderState.CANCELED;
        OrderState stateToChange = OrderState.DONE;
        
        boolean result = currentState.isAvaliableStateToChange(stateToChange);
        
        assertFalse(result);
    }
    
}