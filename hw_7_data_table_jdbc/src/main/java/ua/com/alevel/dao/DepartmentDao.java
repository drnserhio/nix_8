package ua.com.alevel.dao;

import ua.com.alevel.model.BaseUser;
import ua.com.alevel.model.impl.Department;
import ua.com.alevel.model.impl.DepartmentResponse;
import ua.com.alevel.model.impl.EmployeeResponse;

import java.util.List;

public interface DepartmentDao <E extends BaseUser> {
//    Map<String , Object> findEmployeeById(Long id);
//    Map<String, Object> findDepartmentById(Long id);
//    ResponseTable<E> findAll(ResponseTable responseTable);
//    long count();

    void createDepartment(Department create);
    void updateDepartment(Department update);
    void deleteDepartment(Long id);
    E findById(Long id);
    boolean existsDepartmentById(Long id);

    List<E> findAll();

    void addEmployeeForDepartment(Long department_id, Long employee_id);
    void deleteEmployeeForDepartment(Long department_id, Long employee_id);
    List<Department> findEmployeesByDepartment(Long id);

    DepartmentResponse findAllLimit(int page, int showEntity);

    DepartmentResponse findAllWithSortColumn(int page, int showEntity, String column,  String sort);
}

