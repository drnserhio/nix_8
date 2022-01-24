package alevel.dto;

import alevel.model.impl.Department;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentResponse {

    private List<Department> departments;
    private int page;
    private int totalPages;
    private int showEntity;
    private int allSizeEntity;
    private String sort;
    private int showEntityTo;
    private int showEntityFrom;
}
