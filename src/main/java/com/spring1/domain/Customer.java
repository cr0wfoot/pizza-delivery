package com.spring1.domain;

public class Customer {
    
    private Integer id;
    private String name;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }
    
    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
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
        return "id=" + id + " name=" + name;
    }

}
