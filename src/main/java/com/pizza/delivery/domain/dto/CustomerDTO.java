package com.pizza.delivery.domain.dto;

import com.pizza.delivery.domain.entities.User;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public class CustomerDTO {
    
    @Pattern(regexp="^\\w$", message="Name can only consist of letters.")
    private String name;
    
    @Pattern(regexp="^\\w$", message="City can only consist of letters.")
    private String city;
    
    @Pattern(regexp="^\\w{4,}$", message="Street can only consist of numbers, letters and the underscore character.")
    private String street;
    
    @Pattern(regexp="^\\w{4,}$", message="Appartment can only consist of numbers, letters and the underscore character.")
    private String appartment;
    
    private Long customerId;
    
    @NotBlank(message="Username cannot be blank.")
    @Size(min=4, max=15, message="Username must be between 4 and 10 characters long.")
    @Pattern(regexp="^\\w{4,}$", message="Username can only consist of numbers, letters and the underscore character.")
    private String login;
    
    @NotBlank(message="Pass cannot be blank.")
    @Size(min=4, max=15, message="Pass must be between 4 and 10 characters long.")
    @Pattern(regexp="^\\w{4,}$", message="Pass can only consist of numbers, letters and the underscore character.")
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
    
    public boolean isAddressComplete() {
        return this.city != null && this.appartment != null && this.street != null;
    }
    
    public void fillDTOFromUser(User user) {
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