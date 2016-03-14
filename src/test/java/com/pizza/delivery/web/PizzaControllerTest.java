package com.pizza.delivery.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.NestedServletException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { 
    "classpath:/repositoryContext.xml", 
    "classpath:/discountContext.xml",
    "classpath:/appContext.xml", 
    "file:src/main/webapp/WEB-INF/webContext.xml"})
@ActiveProfiles("prod")
@Transactional
public class PizzaControllerTest {
    
    @Autowired
    private PizzaController instance;
    
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(instance).setViewResolvers(viewResolver).build();
    }

    /**
     * Test of view method, of class PizzaController.
     */
    @Test
    public void testView() throws Exception {
        mockMvc.perform(get("/pizza/pizzas")).andExpect(view().name("pizzas"));
    }

    /**
     * Test of findById method, of class PizzaController.
     */
    @Test
    public void testFindById() {
        //not supported
    }

    /**
     * Test of create method, of class PizzaController.
     */
    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(get("/pizza/create")).andExpect(view().name("pizzaform"));
    }

    /**
     * Test of save method, of class PizzaController.
     */
    @Test
    public void testSave() throws Exception {
        mockMvc.perform(post("/pizza/save")
                            .param("name", "Name")
                            .param("type", "SEA")
                            .param("price", "1.0"))
                        .andDo(print())
			.andExpect(redirectedUrl("pizzas"));
    }

    /**
     * Test of edit method, of class PizzaController.
     */
    @Test
    public void testEdit() throws Exception {
        mockMvc.perform(post("/pizza/edit")).andExpect(view().name("pizzaform"));
    }

    /**
     * Test of remove method, of class PizzaController.
     */
    @Test
    public void testRemove() throws Exception {
        mockMvc.perform(post("/pizza/remove")).andExpect(redirectedUrl("pizzas"));
    }

    /**
     * Test of addPizzaToBasket method, of class PizzaController.
     */
    @Test
    public void testAddPizzaToBasket() throws Exception {
        mockMvc.perform(post("/pizza/basket-add")).andExpect(redirectedUrl("pizzas"));
    }

    /**
     * Test of removePizzaFromBasket method, of class PizzaController.
     */
    @Test
    public void testRemovePizzaFromBasket() throws Exception {
        mockMvc.perform(post("/pizza/basket-del")).andExpect(redirectedUrl("basket"));
    }

    /**
     * Test of goToBasket method, of class PizzaController.
     */
    @Test
    public void testGoToBasket() throws Exception {
        mockMvc.perform(get("/pizza/basket")).andExpect(view().name("basket"));
    }
    
}