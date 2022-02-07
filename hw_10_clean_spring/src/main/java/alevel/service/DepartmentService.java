package alevel.service;

import alevel.dao.impl.DepartmentDaoImpl;
import alevel.dto.DepartmentResponse;
import alevel.model.impl.Department;
import alevel.model.impl.Employee;

import java.util.List;
import java.util.Map;


public class DepartmentService {

    private final DepartmentDaoImpl departmentDao;

    public DepartmentService(DepartmentDaoImpl departmentDao) {
        this.departmentDao = departmentDao;
    }

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

    public DepartmentResponse findAllWithSortColumn(int page, int showEntity, String column, String sort) {

        DepartmentResponse response = departmentDao.findAllWithSortColumn(page, showEntity, column, sort);
        return response;
    }

    public List<Employee> listEmployees(int department_id) {
        return departmentDao.listEmployees(department_id);
    }

    public List<Employee> findFreeEmployeesByDepartment(Long id) {
        return departmentDao.findFreeEmployeesByDepartment(id);
    }
}
