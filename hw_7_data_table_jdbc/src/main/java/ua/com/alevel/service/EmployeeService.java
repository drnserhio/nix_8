package ua.com.alevel.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.model.impl.Employee;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService{

    private final EmployeeDao employeeDao;

    public void createEmployee(Employee employee) {
        employeeDao.create(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeDao.update(employee);
    }

    public void deleteEmployee(Long id) {
        employeeDao.delete(id);
    }

    public Employee findById(Long id) {
       return employeeDao.findById(id);
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
}
