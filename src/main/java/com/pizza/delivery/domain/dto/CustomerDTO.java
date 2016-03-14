package com.pizza.delivery.domain.dto;

import com.pizza.delivery.domain.entities.User;
import com.pizza.delivery.domain.validation.UniqueLogin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 * DTO, contains information of User class, Customer class, Address class
 * @see User
 * @see Customer
 * @see Address
 */
public class CustomerDTO {
    
    @Pattern(regexp="^(|[A-Z]'?([a-zA-Z]|\\.| |-)+)$", message="Name can only consist of letters.")
    private String name;
    
    @Pattern(regexp="^(|[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*)$", message="City is invalid.")
    private String city;
    
    @Pattern(regexp="^(|[A-Z]'?([a-zA-Z0-9]|\\.| |-)+[0-9])$", message="Street is invalid.")
    private String street;
    
    @Pattern(regexp="^(|[1-9][0-9A-Za-z-\\s/]{0,})$", message="Appartment is invalid.")
    private String appartment;
    
    private Long customerId;
    
    @NotBlank(message="Username can not be blank.")
    @Size(min=3, max=16, message="Username must be between 3 and 16 characters long.")
    @Pattern(regexp="^[a-zA-Z0-9_-]+$", message="Username can only consist of numbers, letters and the underscore character.")
    @UniqueLogin(message = "Login has already taken.")
    private String login;
    
    @NotBlank(message="Pass cannot be blank.")
    @Size(min=5, max=18, message="Password must be between 5 and 18 characters long.")
    @Pattern(regexp="^[a-zA-Z0-9_-]+$", message="Pass can only consist of numbers, letters and the underscore character.")
    private String pass;
    
    private Long userId;

    /**
     * Get the value of userId
     *
     * @return the value of userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Set the value of userId
     *
     * @param userId new value of userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Get the value of pass
     *
     * @return the value of pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * Set the value of pass
     *
     * @param pass new value of pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Get the value of login
     *
     * @return the value of login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the value of login
     *
     * @param login new value of login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setCustomerId(Long id) {
        this.customerId = id;
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
    
    /**
     * Check if address is complete, all fileds of class Address can not be empty
     * @return true if Address is complete and false in the other case
     */
    public boolean isAddressComplete() {
        return this.city != null && !this.city.isEmpty() && 
               this.appartment != null && !this.appartment.isEmpty() && 
               this.street != null && !this.street.isEmpty();
    }
    
    /**
     * Builds DTO from user's info
     * @param user an object of class User
     */
    public void buildDTOWithUser(User user) {
        if(user == null) return;
        this.userId = user.getId();
        this.login = user.getLogin();
        this.pass = user.getPassword();
        if(user.getCustomer() != null) {
            this.customerId = user.getCustomer().getId();
            this.name = user.getCustomer().getName();
            if(user.getCustomer().getAddress() != null) {
                this.appartment = user.getCustomer().getAddress().getAppartment();
                this.city = user.getCustomer().getAddress().getCity();
                this.street = user.getCustomer().getAddress().getStreet();
            }
        }
    }
    
}