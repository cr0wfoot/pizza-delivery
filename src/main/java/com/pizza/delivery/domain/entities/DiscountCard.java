package com.pizza.delivery.domain.entities;

import javax.persistence.*;

/**
 * Entity: discount card of customer, belongs to customer
 * @see Customer
 */
@Entity
@Table(name="cards")
@NamedQueries({
    @NamedQuery(name = "DiscountCard.getAll", query = "select d from DiscountCard d"),
    @NamedQuery(name = "DiscountCard.deleteById", query = "delete from DiscountCard where id = :id")})
public class DiscountCard {

    @Id
    @Column(name="id")
    @SequenceGenerator(name = "pk_id_card", sequenceName = "pk_id_card", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_id_card")
    private Long id;
    
    @Column(name="points")
    private Integer points;
    
    @OneToOne(mappedBy = "card")
    private Customer customer;

    /**
     * Default constructor
     */
    public DiscountCard() {
        this.points = 0;
    }

    /**
     * Constructor initialize fields: id, customer, points
     * @param id the value of id
     * @param customer card's owner
     * @param points the value of discount points on card
     */
    public DiscountCard(Long id, Customer customer, Integer points) {
        this.id = id;
        this.customer = customer;
        this.points = points;
    }

    /**
     * Get the value of points
     *
     * @return the value of points
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * Set the value of points
     *
     * @param points new value of points
     */
    public void setPoints(Integer points) {
        this.points = points;
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

    @Override
    public String toString() {
        return "Discount card{" + id + "} points = " + points;
    }

}