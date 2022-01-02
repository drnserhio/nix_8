package ua.com.alevel.model.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.alevel.model.BaseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Department extends BaseUser {
    private String nameCompany;
    private String address;
    private final List<Employee> employees = new ArrayList<>();

    public Department(Long id, Date create, Date update, String nameCompany, String address) {
        super(id, create, update);
        this.nameCompany = nameCompany;
        this.address = address;
    }

    public Department() {
    }


    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

}
