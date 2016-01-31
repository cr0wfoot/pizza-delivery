package com.spring1.domain;

import java.util.List;

public class Order {

    private static final int MIN_PIZZAS_COUNT = 1;
    private static final int MAX_PIZZAS_COUNT = 10;
    
    private Integer id;
    private Customer customer;
    private List<Pizza> pizzas;
    private Double price;
    private OrderState state;

    public Order() {
        
    }

    public Order(Customer customer, OrderState state) {
        this.customer = customer;
        this.state = state;
    }

    public Order(Integer id, Customer customer, OrderState state) {
        this.id = id;
        this.customer = customer;
        this.state = state;
    }
    
    private Double calculateTotalPrice(List<Pizza> pizzas) {
        Double totalPrice = 0.0;
        for(Pizza p : pizzas)
            totalPrice += p.getPrice();
        return totalPrice;
    }
    
    private void checkPizzasCountRestrictions(int size) {
        if(size < MIN_PIZZAS_COUNT && size > MAX_PIZZAS_COUNT)
            throw new IllegalArgumentException();
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
    
    public void setState(OrderState stateToChange) {
        if(this.state == null || this.state.getAvaliableStatesToChange().contains(stateToChange))
            this.state = stateToChange;
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
        checkPizzasCountRestrictions(pizzas.size());
        this.pizzas = pizzas;
        this.price = calculateTotalPrice(pizzas);
    }
    
    public void addPizzas(List<Pizza> pizzas) {
        checkPizzasCountRestrictions(pizzas.size() + this.pizzas.size());
        this.pizzas.addAll(pizzas);
        this.price = calculateTotalPrice(pizzas);
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
