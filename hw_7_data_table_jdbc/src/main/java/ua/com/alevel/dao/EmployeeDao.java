package ua.com.alevel.dao;

import ua.com.alevel.model.impl.Department;
import ua.com.alevel.model.impl.Employee;
import ua.com.alevel.model.impl.EmployeeResponse;

import java.util.List;

public interface EmployeeDao<E extends Employee> {
//     Map<String , Object> findEmployeeById(Long id);
//     Map<String, Object> findDepartmentById(Long id);
//     ResponseTable<E> findAll(ResponseTable responseTable);
//     long count();

     void createEmployee(Employee create);
     void updateEmployee(Employee update);
     void deleteEmployee(Long id);
     List<E> findAll();
     E findEmployeeById(Long id);
     E findEmployeeByUsername(String username);

     boolean existsById(Long id);
     boolean existsByUsername(String username);

     void addDepartmentForEmployee(Long employee_id, Long department_id);
     void deleteDepartment(Long employee_id, Long department_id);
     List<Department> findDepartmentsByEmployee(Long id);


    EmployeeResponse findAllLimit(int page, int pageSave, int showEntity);

     EmployeeResponse findAllWithSortColumn(int find, int page, int showEntity, String column,  String sort);
}
