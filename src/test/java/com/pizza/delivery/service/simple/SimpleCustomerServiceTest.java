package com.pizza.delivery.service.simple;

import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.Customer;
import com.pizza.delivery.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test of Class SimpleCustomerService
 * @see SimpleCustomerService
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleCustomerServiceTest {
    
    @Mock
    private CustomerRepository repository;
    
    @InjectMocks
    private SimpleCustomerService instance;
    
    public SimpleCustomerServiceTest() {
    }

    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Get default list of customers with three initialized customers
     * @return list of customers 
     */
    private List<Customer> getDefaultListOfCustomers() {
        List<Customer> pizzas = new ArrayList<Customer>();
        pizzas.add(new Customer(1l, "1", null, null));
        pizzas.add(new Customer(2l, "2", null, null));
        pizzas.add(new Customer(3l, "3", null, null));
        return pizzas;
    }
    
    /**
     * Get default initialized Customer object
     * @return customer 
     */
    private Customer getDefaultCustomer() {
        return new Customer(1l, "1", null, null);
    }

    /**
     * Test of find method, of class SimpleCustomerService.
     */
    @Test
    public void testFind_ExistingCustomer_ShouldReturnDefaultCustomer() {
        Long inputId = getDefaultCustomer().getId();
        Customer expectedResult = getDefaultCustomer();
        
        when(repository.read(inputId)).thenReturn(expectedResult);
        Customer result = instance.find(inputId);
        
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of find method, of class SimpleCustomerService.
     */
    @Test
    public void testFind_CustomerWhichNotExist_ShouldReturnNull() {
        Long inputId = getDefaultCustomer().getId();
        
        when(repository.read(inputId)).thenReturn(null);
        Customer result = instance.find(inputId);
        
        assertNull(result);
    }
    
    /**
     * Test of find method, of class SimpleCustomerService.
     */
    @Test
    public void testFind_Null_ShouldReturnNull() {
        Long inputId = null;
        
        when(repository.read(inputId)).thenReturn(null);
        Customer result = instance.find(inputId);
        
        assertNull(result);
    }

    /**
     * Test of findAll method, of class SimpleCustomerService.
     */
    @Test
    public void testFindAll_RepositoryWithCustomers_ShouldReturnDefaultListOfCustomers() {
        List<Customer> expectedResult = getDefaultListOfCustomers();
        
        when(repository.readAll()).thenReturn(expectedResult);
        List result = instance.findAll();
        
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of findAll method, of class SimpleCustomerService.
     */
    @Test
    public void testFindAll_RepositoryWithoutCustomers_ShouldReturnEmptyList() {
        List<Customer> expectedResult = new ArrayList();
        
        when(repository.readAll()).thenReturn(new ArrayList());
        List result = instance.findAll();
        
        assertEquals(expectedResult, result);
    }

    /**
     * Test of createNewCustomer method, of class SimpleCustomerService.
     */
    @Test
    public void testCreateNewCustomer_CustomerWithEmptyFields_ShouldReturnCustomerWithId1() {
        CustomerDTO inputDto = new CustomerDTO();
        Long expectedId = 1l;
        
        when(repository.create(any(Customer.class))).thenReturn(expectedId);
        Customer result = instance.createNewCustomer(inputDto);
        
        assertEquals(expectedId, result.getId());
    }
    
    /**
     * Test of createNewCustomer method, of class SimpleCustomerService.
     */
    @Test
    public void testCreateNewCustomer_Null_ShouldReturnCustomerWithId0() {
        Long expectedId = 0l;
        
        when(repository.create(null)).thenReturn(0l);
        Customer result = instance.createNewCustomer(null);
        
        assertEquals(expectedId, result.getId());
    }
    
    /**
     * Test of createNewCustomer method, of class SimpleCustomerService.
     */
    @Test
    public void testCreateNewCustomer_CustomerWithExistingId1_ShouldReturnCustomerWithId2() {
        CustomerDTO inputDto = new CustomerDTO();
        inputDto.setCustomerId(1l);
        Long expectedId = 2l;
        
        when(repository.create(any(Customer.class))).thenReturn(expectedId);
        when(repository.read(any(Long.class))).thenReturn(new Customer());
        Customer result = instance.createNewCustomer(inputDto);
        
        assertEquals(expectedId, result.getId());
    }

    /**
     * Test of getCustomerFromDTO method, of class SimpleCustomerService.
     */
    @Test
    public void testGetCustomerFromDTO_Null_ShouldReturnEmptyCustomer() {
        CustomerDTO inputDto = null;
        
        Customer result = instance.getCustomerFromDTO(inputDto);
        
        assertNotNull(result);
    }
    
    /**
     * Test of getCustomerFromDTO method, of class SimpleCustomerService.
     */
    @Test
    public void testGetCustomerFromDTO_WithFieldName_ShouldReturnCustomerWithSameName() {
        CustomerDTO inputDto = new CustomerDTO();
        String givenName = "name";
        inputDto.setName(givenName);
        
        Customer result = instance.getCustomerFromDTO(inputDto);
        
        assertEquals(givenName, result.getName());
    }
    
    /**
     * Test of getCustomerFromDTO method, of class SimpleCustomerService.
     */
    @Test
    public void testGetCustomerFromDTO_WithAddress_ShouldReturnCustomerWithAddress() {
        CustomerDTO inputDto = new CustomerDTO();
        inputDto.setCity("");
        inputDto.setAppartment("");
        inputDto.setStreet("");
        
        Customer result = instance.getCustomerFromDTO(inputDto);
        
        assertNotNull(result.getAddress());
    }
    
    /**
     * Test of getCustomerFromDTO method, of class SimpleCustomerService.
     */
    @Test
    public void testGetCustomerFromDTO_WithNotFullAddress_ShouldReturnCustomerWithEmptyAddress() {
        CustomerDTO inputDto = new CustomerDTO();
        inputDto.setCity("");
        inputDto.setAppartment("");
        
        Customer result = instance.getCustomerFromDTO(inputDto);
        
        assertNotNull(result.getAddress());
    }
    
    /**
     * Test of getCustomerFromDTO method, of class SimpleCustomerService.
     */
    @Test
    public void testGetCustomerFromDTO_WithoutAddress_ShouldReturnCustomerWithEmptyAddress() {
        CustomerDTO inputDto = new CustomerDTO();
        
        Customer result = instance.getCustomerFromDTO(inputDto);
        
        assertNotNull(result.getAddress());
    }

    /**
     * Test of update method, of class SimpleCustomerService.
     */
    @Test
    public void testUpdate_Null_UpdateMethodOfRepositoryShouldBeInvoked() {
        Customer inputCustomer = null;
        
        instance.update(inputCustomer);
        
        verify(repository).update(inputCustomer);
    }
    
    /**
     * Test of update method, of class SimpleCustomerService.
     */
    @Test
    public void testUpdate_DefaultCustomer_UpdateMethodOfRepositoryShouldBeInvoked() {
        Customer inputCustomer = getDefaultCustomer();
        
        instance.update(inputCustomer);
        
        verify(repository).update(inputCustomer);
    }

    /**
     * Test of delete method, of class SimpleCustomerService.
     */
    @Test
    public void testDelete_Null_DeleteMethodOfRepositoryShouldBeInvoked() {
        Long inputId = null;
        
        instance.delete(inputId);
        
        verify(repository).delete(inputId);
    }
    
    /**
     * Test of delete method, of class SimpleCustomerService.
     */
    @Test
    public void testDelete_DefaultCustomerId_DeleteMethodOfRepositoryShouldBeInvoked() {
        Long inputId = getDefaultCustomer().getId();
        
        instance.delete(inputId);
        
        verify(repository).delete(inputId);
    }
    
}