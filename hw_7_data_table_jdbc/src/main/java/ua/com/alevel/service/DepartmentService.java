package ua.com.alevel.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.dao.impl.DepartmentDaoImpl;
import ua.com.alevel.model.impl.Department;
import ua.com.alevel.model.impl.Employee;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentDaoImpl departmentDao;

    public void delete(Long id) {
        departmentDao.deleteDepartment(id);
    }

    public Department findById(Long id) {
        return departmentDao.findById(id);
    }

    public List<Employee> findEmployeesByDepartment(Long id) {
        return departmentDao.findEmployeesByDepartment(id);
    }

    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    public void create(Department create) {
        departmentDao.createDepartment(create);

    }

    public void update(Department update) {
        departmentDao.updateDepartment(update);
    }

    public Department findDepartmentByNameCompany(String nameCompany) {
        return departmentDao.findDepartmentByNameCompany(nameCompany);
    }

    public long count() {
        return 0;
    }

    public Map<String, Object> findEmployeeById(Long id) {
        return null;
    }

    public Map<String, Object> findDepartmentById(Long id) {
        return null;
    }

    public void addDepartmentForEmployee(Long department_id, Long employee_id) {
        departmentDao.addEmployeeForDepartment(department_id, employee_id);
    }

    public void deleteEmployeeForDepartment(Long department_id, Long employee_id) {
        departmentDao.deleteEmployeeForDepartment(department_id, employee_id);
    }
}
