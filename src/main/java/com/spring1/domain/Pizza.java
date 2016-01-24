package com.spring1.domain;

public class Pizza {
    
    private Integer id;
    private String name;
    private Double price;
    private Type type;

    public enum Type {
        VEGETARIAN, 
        SEA, 
        MEAT
    }

    public Pizza() {
    }

    public Pizza(Integer id, String name, Double price, Type type) {
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
    public Type getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(Type type) {
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
        return "id=" + id + " name=" + name + " price=" + price + " type=" + type.toString();
    }

}
