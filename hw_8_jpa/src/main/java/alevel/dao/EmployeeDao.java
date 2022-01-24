package alevel.dao;

import alevel.model.impl.Department;
import alevel.model.impl.Employee;
import alevel.dto.EmployeeResponse;



import java.util.List;

public interface EmployeeDao<E extends Employee> {

     void createEmployee(Employee create);
     void updateEmployee(Employee update);
     void deleteEmployee(Long id);
     List<E> findAll();
     E findEmployeeById(Long id);
     E findEmployeeByUsername(String username);

     boolean existsById(Long id);
     boolean existsByUsername(String username);

     void addDepartmentForEmployee(Long employee_id, Long department_id);
     void deleteDepartmentForEmployee(Long employee_id, Long department_id);
     List<Department> findDepartmentsByEmployee(Long id);
     List<Department> findFreeDepartmentByEmployee(Long employeeId);


     EmployeeResponse findAllWithSortColumn(int page, int showEntity, String column, String sort);
}
