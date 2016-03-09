/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.delivery.domain.entities;

import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.*;

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

    public OrderDetails() {
    }

    public OrderDetails(PizzaOrder pizzaOrder, Pizza pizza, Double price, Integer pizzasQuantity) {
        this.pizzaOrder = pizzaOrder;
        this.pizza = pizza;
        this.price = price;
        this.pizzasQuantity = pizzasQuantity;
    }

    public Integer getPizzasQuantity() {
        return pizzasQuantity;
    }

    public void setPizzasQuantity(Integer pizzasQuantity) {
        this.pizzasQuantity = pizzasQuantity;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public PizzaOrder getPizzaOrder() {
        return pizzaOrder;
    }

    public void setPizzaOrder(PizzaOrder pizzaOrder) {
        this.pizzaOrder = pizzaOrder;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    /**
     * Price comparator
     * @return comparator
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