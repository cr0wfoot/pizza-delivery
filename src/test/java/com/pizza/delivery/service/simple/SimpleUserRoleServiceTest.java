package com.pizza.delivery.service.simple;

import com.pizza.delivery.domain.entities.UserRole;
import com.pizza.delivery.repository.UserRoleRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test of Class UserRoleService
 * @see UserRoleService
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleUserRoleServiceTest {
    
    @Mock
    private UserRoleRepository repository;

    @InjectMocks
    private SimpleUserRoleService instance;

    public SimpleUserRoleServiceTest() {
    }
    
    
    /**
     * Test of findByAuthority method, of class SimpleUserRoleService.
     */
    @Test
    public void testFindByAuthority_Null_RepositoryMethodShouldBeInvoked() {
        String authority = null;
        
        instance.findByAuthority(authority);
        
        verify(repository).findByAuthority(authority);
    }
    
    /**
     * Test of findByAuthority method, of class SimpleUserRoleService.
     */
    @Test
    public void testFindByAuthority_Null_ResultShouldBeNull() {
        String authority = null;
        
        UserRole result = instance.findByAuthority(authority);
        
        assertNull(result);
    }
    
    /**
     * Test of findByAuthority method, of class SimpleUserRoleService.
     */
    @Test
    public void testFindByAuthority_LoginNotExist_ShouldBeReturnedNull() {
        String authority = "ROLE_NOTEXIST";
        
        when(repository.findByAuthority(authority)).thenReturn(null);
        UserRole result = instance.findByAuthority(authority);
        
        assertNull(result);
    }
    
    /**
     * Test of findByAuthority method, of class SimpleUserRoleService.
     */
    @Test
    public void testFindByAuthority_LoginExists_ShouldBeReturnedRoleWithGivenAuthority() {
        String authority = "ROLE_USER";
        UserRole expectedRole = new UserRole();
        expectedRole.setAuthority(authority);
                
        
        when(repository.findByAuthority(authority)).thenReturn(expectedRole);
        UserRole result = instance.findByAuthority(authority);
        
        assertEquals(expectedRole.getAuthority(), result.getAuthority());
    }
    
}