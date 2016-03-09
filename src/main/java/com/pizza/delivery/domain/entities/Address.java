package com.pizza.delivery.domain.entities;

import javax.persistence.*;

/**
 * Entity: customer's address
 * @see Customer
 */
@Entity
@Table(name = "addresses")
public class Address implements Cloneable {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "pk_id_address", sequenceName = "pk_id_address", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_id_address")
    private Long id;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "appartment")
    private String appartment;
    
    @OneToOne(optional = true, mappedBy = "address")
    private Customer customer;

    /**
     * Default constructor
     */
    public Address() {
    }
    
    /**
     * Constructor initialize fields: id, country, city, street, appartment, customer
     * @param id the value of id
     * @param country the value of customer's country
     * @param city the value of customer's city
     * @param street the value of customer's street
     * @param appartment the value of customer's appartment
     * @param customer address's owner
     */
    public Address(Long id, String city, String street, String appartment, Customer customer) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.appartment = appartment;
        this.customer = customer;
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

    /**
     * Get the value of appartment
     *
     * @return the value of appartment
     */
    public String getAppartment() {
        return appartment;
    }

    /**
     * Set the value of appartment
     *
     * @param appartment new value of appartment
     */
    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }

    /**
     * Get the value of street
     *
     * @return the value of street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Set the value of street
     *
     * @param street new value of street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Get the value of city
     *
     * @return the value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the value of city
     *
     * @param city new value of city
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    @Override
    public String toString() {
        return "Address{" + id + "} " + appartment + ", " + street + ", " + city;
    }

    @Override
    public Address clone() {
        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setAppartment(appartment);
        return address;
    }
    
}