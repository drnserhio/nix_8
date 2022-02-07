package alevel.dto;

import alevel.model.impl.Employee;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponse {

    private List<Employee> employees;
    private int page;
    private int totalPages;
    private int showEntity;
    private int allSizeEntity;
    private String sort;
    private int showEntityTo;
    private int showEntityFrom;

}
