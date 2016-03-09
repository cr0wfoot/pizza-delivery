package com.pizza.delivery.domain.dto;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of CustomerDTO
 * @see CustomerDTO
 */
public class CustomerDTOTest {
    
    private CustomerDTO instance;
    
    public CustomerDTOTest() {
    }
    
    @Before
    public void setUp() {
        instance = new CustomerDTO();
    }
    
    @After
    public void tearDown() {
        instance = new CustomerDTO();
    }
    
    /**
     * Test of isAddressExists method, of class CustomerDTO.
     */
    @Test
    public void testIsAddressExists_FullAddressFields_ShouldReturnTrue() {
        instance.setAppartment("");
        instance.setCity("");
        instance.setStreet("");
 
        boolean result = instance.isAddressComplete();
        
        assertTrue(result);
    }
    
    /**
     * Test of isAddressExists method, of class CustomerDTO.
     */
    @Test
    public void testIsAddressExists_AddressNotFull_ShouldReturnFalse() {
        instance.setAppartment("");
        instance.setCity("");
        
        boolean result = instance.isAddressComplete();
        
        assertFalse(result);
    }
    
}