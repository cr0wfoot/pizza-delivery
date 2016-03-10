package com.pizza.delivery.service.simple;

import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.Customer;
import com.pizza.delivery.domain.entities.User;
import com.pizza.delivery.domain.entities.UserRole;
import com.pizza.delivery.repository.UserRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Test of Class UserService
 * @see UserService
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleUserServiceTest {
    
    @Mock
    private UserRepository repository;
    
    @Mock
    private SimpleCustomerService customerService;
    
    @Mock
    private SimpleUserRoleService userRoleService;
    
    @Mock
    private PasswordEncoder encoder;
    
    @InjectMocks
    private SimpleUserService instance;
    
    public SimpleUserServiceTest() {
    }

    /**
     * Test of findByLogin method, of class SimpleUserService.
     */
    @Test
    public void testFindByLogin_Null_RepositoryMethodReadShouldBeInvoked() {
        String login = null;
        
        instance.findByLogin(login);
        
        verify(repository).findByLogin(login);
    }
    
    /**
     * Test of findByLogin method, of class SimpleUserService.
     */
    @Test
    public void testFindByLogin_Null_ResultShouldBeNull() {
        String login = null;
        
        User result = instance.findByLogin(login);
        
        assertNull(result);
    }
    
    /**
     * Test of findByLogin method, of class SimpleUserService.
     */
    @Test
    public void testFindByLogin_LoginNotExist_ShouldBeReturnedNull() {
        String login = "login";
        
        when(repository.findByLogin(login)).thenReturn(null);
        User result = instance.findByLogin(login);
        
        assertNull(result);
    }
    
    /**
     * Test of findByLogin method, of class SimpleUserService.
     */
    @Test
    public void testFindByLogin_LoginExists_ShouldBeReturnedUserWithGivenLogin() {
        String login = "login";
        User expectedUser = new User();
        expectedUser.setLogin(login);
                
        
        when(repository.findByLogin(login)).thenReturn(expectedUser);
        User result = instance.findByLogin(login);
        
        assertEquals(expectedUser.getLogin(), result.getLogin());
    }

    /**
     * Test of registerNewUser method, of class SimpleUserService.
     */
    @Test
    public void testRegisterNewUser_Null_NoneOfMethodsShouldBeInvoked() {
        CustomerDTO dto = null;
        
        when(encoder.encode(any(String.class))).thenReturn("pass");
        when(userRoleService.findByAuthority(any(String.class))).thenReturn(new UserRole("ROLE_USER"));
        instance.registerNewUser(dto);
        
        verify(customerService, never()).createNewCustomer(dto);
        verify(repository, never()).create(any(User.class));
    }
    
    /**
     * Test of registerNewUser method, of class SimpleUserService.
     */
    @Test
    public void testRegisterNewUser_CustomerDto_TwoMethodsShouldBeInvoked() {
        CustomerDTO dto = new CustomerDTO();
        
        when(encoder.encode(any(String.class))).thenReturn("pass");
        when(userRoleService.findByAuthority(any(String.class))).thenReturn(new UserRole("ROLE_USER"));
        instance.registerNewUser(dto);
        
        verify(customerService).createNewCustomer(dto);
        verify(repository).create(any(User.class));
    }

    /**
     * Test of updateUserInfo method, of class SimpleUserService.
     */
    @Test
    public void testUpdateUserInfo_Null_NoneOfMethodsShouldBeInvoked() {
        CustomerDTO dto = null;
        
        when(encoder.encode(any(String.class))).thenReturn("pass");
        instance.updateUserInfo(dto);
        
        verify(customerService, never()).getCustomerFromDTO(dto);
        verify(customerService, never()).update(any(Customer.class));
        verify(repository, never()).update(any(User.class));
    }
    
    /**
     * Test of updateUserInfo method, of class SimpleUserService.
     */
    @Test
    public void testUpdateUserInfo_CustomerDto_ThreeMethodsShouldBeInvoked() {
        CustomerDTO dto = new CustomerDTO();
        
        when(encoder.encode(any(String.class))).thenReturn("pass");
        instance.updateUserInfo(dto);
        
        verify(customerService).getCustomerFromDTO(dto);
        verify(customerService).update(any(Customer.class));
        verify(repository).update(any(User.class));
    }
  
}