package com.pizza.delivery.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * State of order, every state has states avaliable to change for
 * @see PizzaOrder
 */
public enum OrderState {
        
    /**
     * State for done order, final state
     */
    DONE       (),
      
    /**
     * State for canceled order, final state
     */
    CANCELED   (),
      
    /**
     * State for order in progress, can be canceled or done
     */
    IN_PROGRSS ( CANCELED , DONE       ),
       
    /**
     * State for new order, can be canceled or become in progress
     */
    NEW        ( CANCELED , IN_PROGRSS );
        
    private List<OrderState> permissions;
        
    private OrderState(OrderState...states) {
        permissions = new ArrayList<OrderState>(Arrays.asList(states));
    }
        
    /**
     * Get all avaliable states to change for current order's state
     * @return list of states
     */
    public List<OrderState> getAvaliableStatesToChange() {
        return permissions;
    }
        
    /**
     * Check if current order's state can be changed on given state
     * @param stateToChange given state
     * @return true if possible to change and false if not
     */
    public boolean isAvaliableStateToChange(OrderState stateToChange) {
        if(stateToChange == null) return false;
        return permissions.contains(stateToChange);
    }
        
}