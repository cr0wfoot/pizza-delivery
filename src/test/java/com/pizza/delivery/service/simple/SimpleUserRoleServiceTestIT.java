package com.pizza.delivery.service.simple;

import com.pizza.delivery.domain.entities.UserRole;
import com.pizza.delivery.service.UserRoleService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
    "classpath:/repositoryContext.xml", 
    "classpath:/discountContext.xml", 
    "classpath:/appContext.xml" })
@ActiveProfiles(profiles = "prod")
@Transactional
public class SimpleUserRoleServiceTestIT extends AbstractTransactionalJUnit4SpringContextTests {
    
    @Autowired
    private UserRoleService userRoleService;
    
    private Long insertRole(final String sql) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
        }, keyHolder);
        return (Long)keyHolder.getKeys().get("id");
    }
    
    @Test
    public void testFind() {
        String sql = "INSERT INTO authorities (id, authority) VALUES(100, 'ROLE_USER')";
        insertRole(sql);
        String isertedAuthority = "ROLE_USER";
        
        UserRole result = userRoleService.findByAuthority(isertedAuthority);
        
        assertNotNull(result);
    }
    
}