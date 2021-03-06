package ua.com.alevel.model.impl;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.alevel.model.BaseUser;

import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Employee extends BaseUser {

    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("username")
    private String username;

    public Employee(Long id, Date create, Date update, String firstname, String lastname, String username) {
        super(id, create, update);
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
    }

    public Employee() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
