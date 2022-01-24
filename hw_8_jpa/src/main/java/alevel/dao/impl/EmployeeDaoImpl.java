package alevel.dao.impl;

import alevel.dao.EmployeeDao;
import alevel.dto.DepartmentResponse;
import alevel.dto.EmployeeResponse;
import alevel.exception.UsernameExistsException;
import alevel.model.impl.Department;
import alevel.model.impl.Employee;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static alevel.constant.DepartmentSqlConstant.FIND_DEPARTMENT_BY_ID;
import static alevel.constant.EmployeeSqlConstant.*;
import static java.lang.System.out;


@Service
@AllArgsConstructor
@Slf4j
public class EmployeeDaoImpl implements EmployeeDao {

    private EntityManager entityManager;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deleteEmployee(Long id) {
        try {
            entityManager
                    .createQuery("delete from Employee as employee where employee.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Employee findEmployeeById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        Employee byId = findEmployeeById(id);
        if (byId == null) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        Employee employeeByUsername = findEmployeeByUsername(username);
        if (employeeByUsername == null) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findEmployeeByUsername(String username) {
        Employee employee = null;
        try {
            Query query = entityManager
                    .createQuery("select  emp from Employee emp where emp.username = :username", Employee.class)
                    .setParameter("username", username);

            List<Employee> resultList = query.getResultList();
            employee = resultList.get(0);

        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return employee;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return (List<Employee>) entityManager
                .createQuery("select emp from Employee emp")
                .getResultList();
    }

    @Transactional(readOnly = true)
    public int countEmployeeEntity() {
        int count = 0;
        try {
            Query query = entityManager
                    .createQuery("select (id) from Employee ");
            count = query.getResultList().size();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse findAllWithSortColumn(int page, int showEntity, String column, String sort) {
        List<Employee> list = new ArrayList<>();
        int firstPage = (page - 1) * showEntity;
        log.debug(sort);

        try {
            String sql = String.format("select * from employees order by %s %s limit %d, %d", column, sort, firstPage, showEntity);
            Query query = entityManager
                    .createNativeQuery(sql, Employee.class);
            list = query.getResultList();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }


        int totalPages = 0;
        int itemSize = countEmployeeEntity();
        if (itemSize % showEntity == 0) {
            totalPages = (itemSize / showEntity);
        } else {
            totalPages = (itemSize / showEntity) + 1;
        }

        EmployeeResponse departmentResponse = new EmployeeResponse();

        departmentResponse.setEmployees(list);
        departmentResponse.setPage(page);
        departmentResponse.setTotalPages(totalPages);
        departmentResponse.setShowEntity(showEntity);
        departmentResponse.setAllSizeEntity(itemSize);
        departmentResponse.setSort(sort);
        return departmentResponse;
    }

    private int totalPage(int itemSize, int showEntity) {
        if (itemSize % showEntity == 0) {
            return (itemSize / showEntity);
        } else {
            return (itemSize / showEntity) + 1;
        }
    }

    private int showEntriesTo(int entFrom, int itemSize) {
        return entFrom - 1 + itemSize;
    }

    private int showEntriesFrom(int page, int showEntity) {
        return (page - 1) * showEntity + 1;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void createEmployee(Employee create) {
        if (existsByUsername(create.getUsername())) {
            throw new UsernameExistsException("Username already taken: " + create.getUsername());
        }
        try {
            entityManager.persist(create);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void updateEmployee(Employee update) {
        if (existsById(update.getId())) {
            Employee employee = findEmployeeByUsername(update.getUsername());
            if (employee == null) {
                entityManager.merge(update);
            } else if (employee.getId().equals(update.getId())) {
                employee.setFirstname(update.getFirstname());
                employee.setLastname(update.getLastname());
                employee.setUsername(update.getUsername());
                employee.setDepartments(update.getDepartments());
                entityManager.merge(employee);
            }
        } else {
            throw new UsernameExistsException("Employee don't have with id: " + update.getId());
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void addDepartmentForEmployee(Long employee_id, Long department_id) {
        if (findEmployeeById(employee_id) == null) {
            throw new RuntimeException("Employee don't find with id: " + employee_id);
        }
        if (findDepartmentById(department_id) == null) {
            throw new RuntimeException("Department don't find with id: " + department_id);
        }

        try {
            entityManager
                    .createNativeQuery("insert into relations values (:dep_id, :emp_id)")
                    .setParameter("dep_id", department_id)
                    .setParameter("emp_id", employee_id)
                    .executeUpdate();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Department findDepartmentById(Long department_id) {
        Department department = null;
        try  {
          department = entityManager.find(Department.class, department_id);

        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return department;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deleteDepartmentForEmployee(Long department_id, Long employee_id) {
        if (findEmployeeById(employee_id) == null) {
            throw new RuntimeException("Employee don't find with id: " + employee_id);
        }
        if (findDepartmentById(department_id) == null) {
            throw new RuntimeException("Department don't find with id: " + department_id);
        }
        try {
            entityManager
                    .createNativeQuery("delete from relations where department_id = :dep_id and employee_id = :emp_id")
                    .setParameter("dep_id", department_id)
                    .setParameter("emp_id", employee_id)
                    .executeUpdate();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findDepartmentsByEmployee(Long id) {
        List<Department> departments = new ArrayList<>();
        try {
            Query query = entityManager
                    .createNativeQuery("select id, address, nameCompany from departments where departments.id in (select department_id from relations where employee_id = :id)", Department.class)
                    .setParameter("id", id);
            departments = query.getResultList();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return departments;
    }

    @Transactional(readOnly = true)
    public List<Department> findFreeDepartmentByEmployee(Long employeeId) {
        List<Department> departments = new ArrayList<>();

        try {
            Query query = entityManager
                    .createNativeQuery("select id, address, nameCompany from departments where id not in (select department_id from relations where employee_id = :emp_id)", Department.class)
                    .setParameter("emp_id", employeeId);
            departments = query.getResultList();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return departments;
    }


}
