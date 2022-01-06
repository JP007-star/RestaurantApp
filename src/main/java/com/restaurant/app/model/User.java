/*
 * @project restaurantApp
 * @fileName user
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 24 12 2021 06:39 PM
 */
package com.restaurant.app.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name =  "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    private Long phoneNo;
    private boolean status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> roles;

    public User() {
    }



    public User(String userId, String firstName, String lastName, String email, String phoneNo,boolean status) {
        this.id =  Long.valueOf(userId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = Long.valueOf(phoneNo);
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNo=" + phoneNo +
                ", status=" + status +
                ", roles=" + roles +
                '}';
    }

    public User(Long id, String firstName, String lastName, String email, String password, Long phoneNo, boolean status, Collection<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.status = status;
        this.roles = roles;
    }

    public <T> User(String firstName, String lastName, String email, Long phoneNo, boolean status, String encode, Collection<Role> roles) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = encode;
        this.phoneNo = phoneNo;
        this.status = status;
        this.roles = roles;
    }


    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Collection<Role> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

}
