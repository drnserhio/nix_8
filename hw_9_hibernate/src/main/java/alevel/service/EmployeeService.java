package alevel.service;

import alevel.dao.EmployeeDao;
import alevel.dto.EmployeeResponse;
import alevel.model.impl.Department;
import alevel.model.impl.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeDao employeeDao;

    public void createEmployee(Employee employee) {
        employeeDao.createEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(Long id) {
        employeeDao.deleteEmployee(id);
    }

    public Employee findById(Long id) {
        return employeeDao.findEmployeeById(id);
    }

    public boolean existsById(Long id) {
        return employeeDao.existsById(id);
    }

    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    public Employee findByUsername(String username) {
        return employeeDao.findEmployeeByUsername(username);
    }

    public boolean existEmployeeByUsername(String username) {
        return employeeDao.existsByUsername(username);
    }

    public void addDepartmentForEmployee(Long employee_id, Long department_id) {
        employeeDao.addDepartmentForEmployee(employee_id, department_id);
    }

    public List<Department> findDepartmentsByEmployee(Long id) {
        return employeeDao.findDepartmentsByEmployee(id);
    }

    public void deleteDepartment(Long department_id, Long employee_id) {
        employeeDao.deleteDepartmentForEmployee(department_id, employee_id);
    }

    public EmployeeResponse findAllWithSortColumn(int page, int showEntity, String column, String sort) {
        EmployeeResponse response = employeeDao.findAllWithSortColumn(page, showEntity, column, sort);
        return response;
    }

    public List<Department> findFreeDepartmentByEmployee(Long employeeId) {
        return employeeDao.findFreeDepartmentByEmployee(employeeId);
    }
}
