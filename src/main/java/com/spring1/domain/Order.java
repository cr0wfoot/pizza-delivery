package com.spring1.domain;

import java.util.List;

public class Order {

    private Integer id;
    private Customer customer;
    private List<Pizza> pizzas;

    public Order() {
        
    }

    public Order(Customer customer, List<Pizza> pizzas) {
        this.customer = customer;
        this.pizzas = pizzas;
    }

    public Order(Integer id, Customer customer, List<Pizza> pizzas) {
        this.id = id;
        this.customer = customer;
        this.pizzas = pizzas;
    }
    
    /**
     * Get the value of pizzas
     *
     * @return the value of pizzas
     */
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Set the value of pizzas
     *
     * @param pizzas new value of pizzas
     */
    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    /**
     * Get the value of customer
     *
     * @return the value of customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set the value of customer
     *
     * @param customer new value of customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String pizzaz = "[";
        for(Pizza p : pizzas)
            pizzaz += "{" + p + "},";
        return "id=" + id + " customer=" + customer.getName() + " pizzas=" + pizzaz + "]";
    }
    
}
