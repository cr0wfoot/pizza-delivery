package com.pizza.delivery.domain.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Entity: user, contains authority roles, has customer. 
 * @see UserRole
 * @see Customer
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "User.getAll", query = "select u from User u"),
    @NamedQuery(name = "User.getByLogin", query = "select u from User u where u.login = :login"),
    @NamedQuery(name = "User.getByLoginFetchEager", query = "select u from User u join fetch u.roles join fetch u.customer where u.login = :login"),
    @NamedQuery(name = "User.deleteById", query = "delete from User where id = :id")})
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "login")
    private String login;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "active")
    private Boolean active;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, 
              orphanRemoval = true,
              fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_authority", 
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, 
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private Set<UserRole> roles = new HashSet<UserRole>();

    /**
     * An empty constructor
     */
    public User() {
    }
    
    /**
     * Get the value of roles
     *
     * @return the value of roles
     */
    public Set<UserRole> getRoles() {
        return roles;
    }

    /**
     * Set the value of roles
     *
     * @param roles new value of roles
     */
    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
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
     * Get the value of active
     *
     * @return the value of active
     */
    public Boolean isActive() {
        return active;
    }

    /**
     * Set the value of active
     *
     * @param active new value of active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
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
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login;
    }

}