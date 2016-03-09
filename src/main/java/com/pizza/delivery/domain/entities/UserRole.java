package com.pizza.delivery.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@NamedQueries({
    @NamedQuery(name = "UserRole.getByAuthority", query = "select r from UserRole r where r.authority = :authority")})
public class UserRole {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "pk_id_authority", sequenceName = "pk_id_authority", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_id_authority")
    private Long id;
    
    @Column(name = "authority")
    private String authority;

    public UserRole() {
    }

    public UserRole(String authority) {
        this.authority = authority;
    }

    /**
     * Get the value of authority
     *
     * @return the value of authority
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Set the value of authority
     *
     * @param authority new value of authority
     */
    public void setAuthority(String authority) {
        this.authority = authority;
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
        final UserRole other = (UserRole) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.authority == null) ? (other.authority != null) : !this.authority.equals(other.authority)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 17 * hash + (this.authority != null ? this.authority.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "UserRole{" + id + "} - " + authority;
    }

}