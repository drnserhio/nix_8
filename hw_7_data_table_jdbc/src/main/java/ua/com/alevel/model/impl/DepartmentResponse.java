package ua.com.alevel.model.impl;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentResponse {

    private List<Department> departments;
    private int page;
    private int countEntity;
    private int showEntity;
    private int allSizeEntity;
    private String sort;
}
