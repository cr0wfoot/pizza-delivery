package com.pizza.delivery.domain.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Entity: customer, can have one address and one discount card
 * @see Address
 * @see DiscountCard
 * @see PizzaOrder
 */
@Entity
@Table(name = "customers")
@NamedQueries({
    @NamedQuery(name = "Customer.getAll", query = "select c from Customer c"),
    @NamedQuery(name = "Customer.deleteById", query = "delete from Customer where id = :id")})
public class Customer {
    
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "pk_id_customer", sequenceName = "pk_id_customer", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_id_customer")
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;
    
    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "card_id")
    private DiscountCard card;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", orphanRemoval = true)
    private List<PizzaOrder> orders = new ArrayList<PizzaOrder>();

    /**
     * Get the value of orders
     *
     * @return the value of orders
     */
    public List<PizzaOrder> getOrders() {
        return orders;
    }

    /**
     * Set the value of orders
     *
     * @param orders new value of orders
     */
    public void setOrders(List<PizzaOrder> orders) {
        this.orders = orders;
    }


    /**
     * Default constructor
     */
    public Customer() {
    }
    
    /**
     * Constructor initialize fields: id, name, address, card
     * @param id the value of id
     * @param name the value of name
     * @param address customer's address
     * @param card customer's discount card
     */
    public Customer(Long id, String name, Address address, DiscountCard card) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.card = card;
    }
    
    /**
     * Get the value of card
     *
     * @return the value of card
     */
    public DiscountCard getCard() {
        return card;
    }

    /**
     * Set the value of card
     *
     * @param card new value of card
     */
    public void setCard(DiscountCard card) {
        this.card = card;
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
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
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
    
    /**
     * Check if customer has discount card
     * @return true if discount card exists and false if not
     */
    public boolean isDiscountCardExists() {
        return card != null;
    }
    
    @Override
    public String toString() {
        return "Customer{" + id + "} " + name;
    }

}