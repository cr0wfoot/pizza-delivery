package com.spring1.domain;

import java.util.List;

public class Order {

    private Integer id;
    private Customer customer;
    private List<Pizza> pizzas;
    private Double price;
    private OrderState state;

    public Order() {
        
    }

    public Order(Customer customer, List<Pizza> pizzas, OrderState state, Double price) {
        this.customer = customer;
        this.pizzas = pizzas;
        this.price = price;
        this.state = state;
    }

    public Order(Integer id, Customer customer, List<Pizza> pizzas, Double price, OrderState state) {
        this.id = id;
        this.customer = customer;
        this.pizzas = pizzas;
        this.price = price;
        this.state = state;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    public OrderState getState() {
        return state;
    }
    
    public void setState(OrderState state) {
        this.state = state;
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
    
    public void addPizzas(List<Pizza> pizzas) {
        this.pizzas.addAll(pizzas);
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
