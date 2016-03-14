package com.pizza.delivery.service.simple;

import com.pizza.delivery.domain.entities.Pizza;
import com.pizza.delivery.domain.entities.Pizza.PizzaType;
import com.pizza.delivery.service.PizzaService;
import java.sql.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
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
public class SimplePizzaServiceTestIT extends AbstractTransactionalJUnit4SpringContextTests {
    
    @Autowired
    private PizzaService pizzaService;
    
    @PersistenceContext
    private EntityManager em;
    
    private RowMapper<Pizza> rowMapper = new RowMapper<Pizza>() {
        @Override
        public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pizza pizza = new Pizza();
            pizza.setId(rs.getLong("id"));
            pizza.setName(rs.getString("name"));
            pizza.setPrice(rs.getDouble("price"));
            pizza.setType(PizzaType.valueOf(rs.getString("type")));
            return pizza;
        }       
    };
    
    private Long insertPizza(final String sql) {
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
        String sql = "INSERT INTO pizzas (id, name, price, type) VALUES(100, 'Test1', '1.0', 'SEA')";
        Long id = insertPizza(sql);
        
        Pizza result = pizzaService.find(id);
        
        assertNotNull(result);
    }
    
    @Test
    public void testFindAll() {
        String sql1 = "INSERT INTO pizzas (id, name, price, type) VALUES(100, 'Test1', '1.0', 'SEA')";
        String sql2 = "INSERT INTO pizzas (id, name, price, type) VALUES(101, 'Test2', '2.0', 'SEA')";
        insertPizza(sql1);
        insertPizza(sql2);
        int expectedPizzaQuantity = 2;
        
        List<Pizza> result = pizzaService.findAll();
        
        assertTrue(result.size() >= expectedPizzaQuantity);
    }
    
    @Test
    public void testSave_CreateNew() {
        Pizza pizzaToSave = new Pizza(null, "Test1", 1.0, PizzaType.MEAT);
        int numberOfPizzasBefore = jdbcTemplate.query("SELECT * FROM pizzas", rowMapper).size();
        
        pizzaService.save(pizzaToSave);
        em.flush();
        int numberOfPizzasAfter = jdbcTemplate.query("SELECT * FROM pizzas", rowMapper).size();

        assertEquals(numberOfPizzasBefore + 1, numberOfPizzasAfter);
    }
    
    @Test
    public void testSave_UpdateExisting() {
        String sql = "INSERT INTO pizzas (id, name, price, type) VALUES(101, 'Test1', '1.0', 'SEA')";
        insertPizza(sql);
        String newName = "Test2";
        Long id = 101l;
        Pizza pizzaToUpdate = new Pizza(id, newName, 1.0, PizzaType.MEAT);
        
        pizzaService.save(pizzaToUpdate);
        em.flush();
        Pizza resultPizza = jdbcTemplate.queryForObject("SELECT * FROM pizzas WHERE id=?", new Object[] {id}, rowMapper);

        assertEquals(newName, resultPizza.getName());
    }
    
    @Test(expected = EmptyResultDataAccessException.class)
    public void testDelete() {
        String sql = "INSERT INTO pizzas (id, name, price, type) VALUES(101, 'Test1', '1.0', 'SEA')";
        insertPizza(sql);
        Long id = 101l;
        
        pizzaService.delete(id);
        em.flush();
        Pizza resultPizza = jdbcTemplate.queryForObject("SELECT * FROM pizzas WHERE id=?", new Object[] {id}, rowMapper);
    }
    
}