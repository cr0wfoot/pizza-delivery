package com.pizza.delivery.web;

import com.pizza.delivery.domain.Basket;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { 
    "classpath:/repositoryContext.xml", 
    "classpath:/discountContext.xml",
    "classpath:/appContext.xml", 
    "file:src/main/webapp/WEB-INF/webContext.xml"})
@ActiveProfiles("prod")
@Transactional
public class OrderControllerTest {
    
    @Autowired
    private OrderController instance;
    
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(instance).setViewResolvers(viewResolver).build();
    }

    /**
     * Test of view method, of class OrderController.
     */
    @Test
    public void testView() throws Exception {
        mockMvc.perform(get("/order/orders")).andExpect(view().name("orders"));
    }

    /**
     * Test of create method, of class OrderController.
     */
    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(post("/order/create")
                            .sessionAttr("basket", new Basket()))
                        .andExpect(redirectedUrl("/pizza/pizzas"));
    }

    /**
     * Test of changeState method, of class OrderController.
     */
    @Test
    public void testChangeState() throws Exception {
        mockMvc.perform(post("/order/newstate")
                            .param("state", "NEW")
                            .param("id", "1"))
                        .andDo(print())
                        .andExpect(redirectedUrl("/order/1"));
    }

    /**
     * Test of edit method, of class OrderController.
     */
    @Test
    public void testEdit() throws Exception {
        mockMvc.perform(get("/order/1")).andExpect(view().name("order"));
    }
}
