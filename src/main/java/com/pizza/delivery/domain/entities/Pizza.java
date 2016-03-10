package com.pizza.delivery.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Entity: pizza
 * @see PizzaType
 */
@Entity
@Table(name = "pizzas")
@NamedQueries({
    @NamedQuery(name = "Pizza.getAll", query = "select p from Pizza p"),
    @NamedQuery(name = "Pizza.deleteById", query = "delete from Pizza where id = :id")})
public class Pizza {
    
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "pk_id_pizza", sequenceName = "pk_id_pizza", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_id_pizza")
    private Long id;
    
    @Column(name = "name")
    @NotBlank(message="Name cannot be blank.")
    @Pattern(regexp="^\\w{4,}$", message="Name can only consist of numbers, letters and the underscore character.")
    private String name;
    
    @Column(name = "price")
    @Digits(message = "Format must be xxx.xx", fraction = 2, integer = 3)
    private Double price;
    
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PizzaType type;

    /**
     * Type of pizza
     */
    public enum PizzaType {
        
        /**
         * Pizza without meat
         */
        VEGETARIAN, 
        
        /**
         * Pizza with fish
         */
        SEA, 
        
        /**
         * Pizza with meat
         */
        MEAT
        
    }

    /**
     * Default constructor
     */
    public Pizza() {
    }

    /**
     * Constructor initialize fields: id, name, price, type
     * @param id the value of id
     * @param name the value of name
     * @param price the value of price
     * @param type the value of pizza's type
     */
    public Pizza(Long id, String name, Double price, PizzaType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }
    
    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public PizzaType getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(PizzaType type) {
        this.type = type;
    }


    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Set the value of price
     *
     * @param price new value of price
     */
    public void setPrice(Double price) {
        this.price = price;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pizza other = (Pizza) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Pizza{" + id + "} " + name + " (" + type + ") price = " + price;
    }

}