package com.pizza.delivery.service.simple;

import com.pizza.delivery.domain.entities.Customer;
import com.pizza.delivery.domain.entities.DiscountCard;
import com.pizza.delivery.repository.DiscountCardRepository;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test of Class DiscountCardService
 * @see DiscountCardService
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleDiscountCardServiceTest {
    
    @Mock
    private DiscountCardRepository repository;
    
    @Mock
    private SimpleCustomerService customerService;
    
    @InjectMocks
    private SimpleDiscountCardService instance;
    
    public SimpleDiscountCardServiceTest() {
    }

    /**
     * Test of withdrawPoints method, of class SimpleDiscountCardService.
     */
    @Test
    public void testWithdrawPoints_NotEnoughPointsOnCard_UpdateMethodOfRepositoryShouldBeInvoked() {
        DiscountCard card = new DiscountCard();
        Integer pointsToWithdraw = 10;
        
        instance.withdrawPoints(card, pointsToWithdraw);
        
        verify(repository).update(card);
    }
    
    /**
     * Test of withdrawPoints method, of class SimpleDiscountCardService.
     */
    @Test
    public void testWithdrawPoints_EnoughPointsOnCard_UpdateMethodOfRepositoryShouldBeInvoked() {
        DiscountCard card = new DiscountCard();
        card.setPoints(20);
        Integer pointsToWithdraw = 10;
        
        instance.withdrawPoints(card, pointsToWithdraw);
        
        verify(repository).update(card);
    }
    
    /**
     * Test of withdrawPoints method, of class SimpleDiscountCardService.
     */
    @Test
    public void testWithdrawPoints_NullCard_UpdateMethodOfRepositoryShouldNotBeInvoked() {
        DiscountCard card = null;
        Integer pointsToWithdraw = 10;
        
        instance.withdrawPoints(card, pointsToWithdraw);
        
        verify(repository, never()).update(card);
    }
    
    /**
     * Test of withdrawPoints method, of class SimpleDiscountCardService.
     */
    @Test
    public void testWithdrawPoints_NullPoints_UpdateMethodOfRepositoryShouldNotBeInvoked() {
        DiscountCard card = new DiscountCard();
        Integer pointsToWithdraw = null;
        
        instance.withdrawPoints(card, pointsToWithdraw);
        
        verify(repository, never()).update(card);
    }
    
    /**
     * Test of chargePoints method, of class SimpleDiscountCardService.
     */
    @Test
    public void testChargePoints_NullPoints_UpdateMethodOfRepositoryShouldNotBeInvoked() {
        DiscountCard card = new DiscountCard();
        Integer pointsToCharge = null;
        
        instance.withdrawPoints(card, pointsToCharge);
        
        verify(repository, never()).update(card);
    }
    
    /**
     * Test of chargePoints method, of class SimpleDiscountCardService.
     */
    @Test
    public void testChargePoints_NullCard_UpdateMethodOfRepositoryShouldNotBeInvoked() {
        DiscountCard card = null;
        Integer pointsToCharge = 10;
        
        instance.withdrawPoints(card, pointsToCharge);
        
        verify(repository, never()).update(card);
    }
    
    /**
     * Test of chargePoints method, of class SimpleDiscountCardService.
     */
    @Test
    public void testChargePoints_AddPoints_UpdateMethodOfRepositoryShouldBeInvoked() {
        DiscountCard card = new DiscountCard();
        card.setPoints(20);
        Integer pointsToCharge = 10;
        
        instance.withdrawPoints(card, pointsToCharge);
        
        verify(repository).update(card);
    }

    /**
     * Test of createDiscountCardForCustomer method, of class SimpleDiscountCardService.
     */
    @Test
    public void testCreateDiscountCardForCustomer_CustomerNull_NoneOfMethodsShouldBeInvoked() {
        Customer customer = null;
        
        instance.createDiscountCardForCustomer(customer);
        
        verify(repository, never()).create(any(DiscountCard.class));
        verify(customerService, never()).update(customer);
    }
    
    /**
     * Test of createDiscountCardForCustomer method, of class SimpleDiscountCardService.
     */
    @Test
    public void testCreateDiscountCardForCustomer_CreateNewCard_CardShouldBeCreatedAndCustomerUpdated() {
        Customer customer = new Customer();
        
        instance.createDiscountCardForCustomer(customer);
        
        verify(repository).create(any(DiscountCard.class));
        verify(customerService).update(customer);
    }
    
    /**
     * Test of createDiscountCardForCustomer method, of class SimpleDiscountCardService.
     */
    @Test
    public void testCreateDiscountCardForCustomer_CreateNewCard_IdOfNewCardShouldBe1() {
        Customer customer = new Customer();
        Long expectedResult = 1l;
        
        when(repository.create(any(DiscountCard.class))).thenReturn(expectedResult);
        instance.createDiscountCardForCustomer(customer);
        
        assertEquals(expectedResult, customer.getCard().getId());
    }

    /**
     * Test of removeDiscountCardFromCustomer method, of class SimpleDiscountCardService.
     */
    @Test
    public void testRemoveDiscountCardFromCustomer_CardNotExist_NeitherDeleteNorUpdateMethodsShouldBeInvoked() {
        Customer customer = new Customer();
        
        instance.removeDiscountCardFromCustomer(customer);
        
        verify(customerService, never()).update(customer);
        verify(repository, never()).delete(any(Long.class));
    }
    
    /**
     * Test of removeDiscountCardFromCustomer method, of class SimpleDiscountCardService.
     */
    @Test
    public void testRemoveDiscountCardFromCustomer_CardExists_UpdateCustomerAndDeleteCardWithId1ShouldBeInvoked() {
        Customer customer = new Customer();
        DiscountCard card = new DiscountCard();
        card.setId(1l);
        customer.setCard(card);
        
        instance.removeDiscountCardFromCustomer(customer);
        
        verify(customerService).update(customer);
        verify(repository).delete(1l);
    }
    
}