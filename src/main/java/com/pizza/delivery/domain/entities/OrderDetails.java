/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.delivery.domain.entities;

import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.*;

/**
 * Relationship between order and pizzas, contains 
 * an information about each pizza in this order
 * @see PizzaOrder
 * @see Pizza
 */
@Entity
@Table(name = "order_pizza")
public class OrderDetails implements Serializable {
  
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private PizzaOrder pizzaOrder;
  
    @Id
    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;
    
    @Column(name="price")
    private Double price;
    
    @Column(name="pizzas_quantity")
    private Integer pizzasQuantity;

    /**
     * An empty constructor
     */
    public OrderDetails() {
    }

    /**
     * Initialize all fields of class
     * @param pizzaOrder an object of class PizzaOrder
     * @param pizza an object of class Pizza
     * @param price pizza's price in this order
     * @param pizzasQuantity pizza's quantity
     */
    public OrderDetails(PizzaOrder pizzaOrder, Pizza pizza, Double price, Integer pizzasQuantity) {
        this.pizzaOrder = pizzaOrder;
        this.pizza = pizza;
        this.price = price;
        this.pizzasQuantity = pizzasQuantity;
    }

    /**
     * Get pizza's quantity
     * @return the int value of pizza's quantity
     */
    public Integer getPizzasQuantity() {
        return pizzasQuantity;
    }

    /**
     * Set pizza's quantity
     * @param pizzasQuantity the int value of pizza's quantity
     */
    public void setPizzasQuantity(Integer pizzasQuantity) {
        this.pizzasQuantity = pizzasQuantity;
    }

    /**
     * Get Pizza
     * @return an objcet of class Pizza
     */
    public Pizza getPizza() {
        return pizza;
    }

    /**
     * Set Pizza
     * @param pizza an objcet of class Pizza
     */
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    /**
     * Get PizzaOrder
     * @return an objcet of class PizzaOrder
     */
    public PizzaOrder getPizzaOrder() {
        return pizzaOrder;
    }

    /**
     * Set PizzaOrder
     * @param pizzaOrder an objcet of class PizzaOrder
     */
    public void setPizzaOrder(PizzaOrder pizzaOrder) {
        this.pizzaOrder = pizzaOrder;
    }

    /**
     * Get pizza's price in this order
     * @return the double value of price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Set pizza's price in this order
     * @param price the double value of price
     */
    public void setPrice(Double price) {
        this.price = price;
    }
    
    /**
     * Price comparator, compare two pizzas by price
     * @return pizza comparator
     */
    public static Comparator<OrderDetails> comparatorByPrice() {
	return new Comparator<OrderDetails>() {
            @Override
            public int compare(OrderDetails o1, OrderDetails o2) {
		return -1 * Double.compare(o1.getPrice(), o2.getPrice());
            }
	};
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderDetails other = (OrderDetails) obj;
        if (this.pizza != other.pizza && (this.pizza == null || !this.pizza.equals(other.pizza))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.pizza != null ? this.pizza.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return pizza + "(" + price + ")";
    }  

}