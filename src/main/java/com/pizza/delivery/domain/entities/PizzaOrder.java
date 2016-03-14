package com.pizza.delivery.domain.entities;

import com.pizza.delivery.domain.OrderState;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity: order, contains pizzas represented by OrderDetails and Address, belongs to Customer, has state OrderState
 * @see Customer
 * @see Pizza
 * @see OrderDetails
 * @see Address
 * @see OrderState
 */
@Entity
@Table(name = "orders")
@NamedQueries({
    @NamedQuery(name = "PizzaOrder.getAll", query = "select po from PizzaOrder po"),
    @NamedQuery(name = "PizzaOrder.getByIdFetchEager", query = "select po from PizzaOrder po join fetch po.details where po.id = :id"),
    @NamedQuery(name = "PizzaOrder.deleteById", query = "delete from PizzaOrder where id = :id")})
public class PizzaOrder {
    
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "pk_id_order", sequenceName = "pk_id_order", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_id_order")
    private Long id;
    
    @Column(name = "order_state")
    @NotNull(message = "Type cannot be blank.")
    @Enumerated(EnumType.STRING)
    private OrderState state;
    
    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, 
               mappedBy = "pizzaOrder", 
               cascade = {CascadeType.PERSIST, CascadeType.MERGE}, 
               orphanRemoval = true)
    private Set<OrderDetails> details = new HashSet<OrderDetails>();
    
    /**
     * Default constructor
     */
    public PizzaOrder() {  
    }

    /**
     * Constructor initialize fields: id, state, customer, price, pizzas
     * @param id the value of id
     * @param customer order's owner
     * @param state order's state
     * @param price the value of price
     * @param pizzas the list of pizzas
     */
    public PizzaOrder(Long id, Customer customer, OrderState state, Double price, Set<OrderDetails> details) {
        this.id = id;
        this.customer = customer;
        this.state = state;
        this.price = price;
        this.details = details;
    }
    
    /**
     * Calculates total price
     * @return the double value of total price
     */
    public Double getTotalPrice() {
        Double totalPrice = 0.0;
        for(OrderDetails d : details) {
            if(d == null) break;
            totalPrice += d.getPrice() * d.getPizzasQuantity();
        } 
        return totalPrice;
    }
    
    /**
     * Add pizza represented by OrderDetails
     * @param details an object of class OrderDetails
     */
    public void addDetails(Set<OrderDetails> details) {
        this.details.addAll(details);
    }
    
    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(Address address) {
        this.address = address;
    }
    
    /**
     * Get the value of current price
     * @return the value of current price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Set the value of current price
     * @param price the value of current price
     */
    public void setPrice(Double price) {
        this.price = price;
    }
    
    /**
     * Get the value of state
     * @return the value of state
     */
    public OrderState getState() {
        return state;
    }
    
    /**
     * Set the value of state
     * @param stateToChange the value of state
     */
    public void setState(OrderState stateToChange) {
        this.state = stateToChange;
    }
    
    /**
     * Get the value of details
     *
     * @return the value of details
     */
    public Set<OrderDetails> getDetails() {
        return details;
    }
    
    /**
     * Set the value of details
     * @param details the value of details
     */
    public void setDetails(Set<OrderDetails> details) {
        this.details = details;
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
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Order{" + id + "}" + ", state:" + state;
    }
    
}