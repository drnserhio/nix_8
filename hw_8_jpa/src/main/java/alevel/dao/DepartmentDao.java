package alevel.dao;

import alevel.model.BaseUser;
import alevel.model.impl.Department;
import alevel.dto.DepartmentResponse;
import alevel.model.impl.Employee;


import java.util.List;

public interface DepartmentDao <E extends BaseUser> {

    void createDepartment(Department create);
    void updateDepartment(Department update);
    void deleteDepartment(Long id);
    E findById(Long id);
    boolean existsDepartmentById(Long id);

    List<E> findAll();

    void addEmployeeForDepartment(Long department_id, Long employee_id);
    void deleteEmployeeForDepartment(Long department_id, Long employee_id);
    List<Department> findEmployeesByDepartment(Long id);

    DepartmentResponse findAllWithSortColumn(int page, int showEntity, String column, String sort);

    List<Employee> findFreeEmployeesByDepartment(Long id);
}

