package ua.com.alevel.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.datatable.*;
import ua.com.alevel.enumeration.Direction;
import ua.com.alevel.model.impl.Department;
import ua.com.alevel.model.impl.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeDao employeeDao;
    private PagingService pagingService;

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
        employeeDao.deleteDepartment(department_id, employee_id);
    }

    public PageArray getEmployeesArray(PagingRequest pagingRequest) {
        PageArray employees = pagingService.getEmployeesArray(pagingRequest, findAll());
        return employees;
    }

    public Page<Employee> getEmployees(PagingRequest pagingRequest) {
        return pagingService.getEmployees(pagingRequest, findAll());
    }

}
