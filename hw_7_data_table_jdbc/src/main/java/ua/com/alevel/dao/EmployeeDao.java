package ua.com.alevel.dao;

import ua.com.alevel.datatable.ResponseTable;
import ua.com.alevel.model.BaseUser;
import ua.com.alevel.model.impl.Department;
import ua.com.alevel.model.impl.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDao<E extends Employee> {
     Map<String , Object> findEmployeeById(Long id);
     Map<String, Object> findDepartmentById(Long id);

     void create(Employee create);
     void update(Employee update);
     void delete(Long id);
     E findById(Long id);
     E findEmployeeByUsername(String username);

     boolean existsById(Long id);
     boolean existsByUsername(String username);

     void addDepartmentForEmployee(Long employee_id, Long department_id);
     void deleteDepartment(Department department);
     List<Department> findDepartmentsByEmployee(Long id);


     List<E> findAll();
     ResponseTable<E> findAll(ResponseTable responseTable);
     long count();

}
