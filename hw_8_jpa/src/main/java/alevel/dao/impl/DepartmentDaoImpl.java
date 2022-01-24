package alevel.dao.impl;

import alevel.dao.DepartmentDao;
import alevel.dto.DepartmentResponse;
import alevel.exception.UsernameExistsException;
import alevel.model.impl.Department;
import alevel.model.impl.Employee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;


@Repository
@AllArgsConstructor
@Slf4j
public class DepartmentDaoImpl implements DepartmentDao {

    private EntityManager entityManager;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deleteDepartment(Long id) {
        try {
            entityManager.createQuery("delete from Department as department where department.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }


    @Override
    @Transactional(readOnly = true)
    public Department findById(Long id) {
        return entityManager.find(Department.class, id);
    }


    @Transactional(readOnly = true)
    @Override
    public boolean existsDepartmentById(Long id) {
        Query query = entityManager.createQuery("select count(id) from Department where id = :id").setParameter("id", id);
        return query.getResultList().size() == 1;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return (List<Department>) entityManager
                .createQuery("select dep from Department dep")
                .getResultList();
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void addEmployeeForDepartment(Long department_id, Long employee_id) {
        if (findById(department_id) == null) {
            throw new RuntimeException("Department don't find with id: " + department_id);
        }
        if (findEmployeeById(employee_id) == null) {
            throw new RuntimeException("Employee don't find with id: " + employee_id);
        }

        try {
            entityManager
                    .createNativeQuery("insert into relations values (:dep_id, :emp_id)")
                    .setParameter("dep_id", department_id).setParameter("emp_id", employee_id)
                    .executeUpdate();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }

    }

    @Transactional(readOnly = true)
    public Employee findEmployeeById(Long employee_id) {
        Employee employee = null;
        try {
            employee = entityManager.find(Employee.class, employee_id);
        } catch (Exception e) {
            out.println(e.getMessage());
        }
        return employee;
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deleteEmployeeForDepartment(Long department_id, Long employee_id) {
        if (findById(department_id) == null) {
            throw new RuntimeException("Department don't find with id: " + department_id);
        }
        if (findEmployeeById(employee_id) == null) {
            throw new RuntimeException("Employee don't find with id: " + employee_id);
        }

        try {
            entityManager
                    .createNativeQuery("delete  from relations where department_id= :dep_id and employee_id = :emp_id")
                    .setParameter("dep_id", department_id)
                    .setParameter("emp_id", employee_id)
                    .executeUpdate();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findEmployeesByDepartment(Long id) {
        List<Employee> employees = new ArrayList<>();
        try {
            Query query = entityManager
                    .createNativeQuery("select id, firstname, lastname, username  from employees  where employees.id  in (select employee_id from relations where department_id = :id)", Employee.class)
                    .setParameter("id", id);
            employees = query.getResultList();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }

        return employees;
    }

    @Transactional(readOnly = true)
    public int countDepartmentEntity() {

        int count = 0;
        try {
            Query query = entityManager
                    .createQuery("select (id) from Department ");
            count = query.getResultList().size();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
//
        return count;
    }


    @Override
    @Transactional(readOnly = true)
    public DepartmentResponse findAllWithSortColumn(int page, int showEntity, String column, String sort) {
        List<Department> list = new ArrayList<>();
        int firstPage = (page - 1) * showEntity;

        try {
            String sql = String.format("select * from departments order by %s %s limit %d, %d", column, sort, firstPage, showEntity);
            Query query = entityManager
                    .createNativeQuery(sql, Department.class);
            list = query.getResultList();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }


        int totalPages = 0;
        int itemSize = countDepartmentEntity();
        if (itemSize % showEntity == 0) {
            totalPages = (itemSize / showEntity);
        } else {
            totalPages = (itemSize / showEntity) + 1;
        }

        DepartmentResponse departmentResponse = new DepartmentResponse();

        departmentResponse.setDepartments(list);
        departmentResponse.setPage(page);
        departmentResponse.setTotalPages(totalPages);
        departmentResponse.setShowEntity(showEntity);
        departmentResponse.setAllSizeEntity(itemSize);
        departmentResponse.setSort(sort);
        return departmentResponse;
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void createDepartment(Department create) {

        if (existsByNameCompany(create.getNameCompany())) {
            throw new UsernameExistsException("Name company already taken");
        }
        try {
            entityManager.persist(create);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public boolean existsByNameCompany(String nameCompany) {
        if (findDepartmentByNameCompany(nameCompany) == null) {
            return false;
        }
        return true;
    }

    @Transactional(readOnly = true)
    public Department findDepartmentByNameCompany(String nameCompany) {
        Department department = null;
        try {
            Query query = entityManager
                    .createQuery("select dep from Department dep where dep.nameCompany = :nameCompany")
                    .setParameter("nameCompany", nameCompany);
            List<Department> resultList = query.getResultList();
            department = resultList.get(0);

        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return department;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public void updateDepartment(Department update) {
        if (existsDepartmentById(update.getId())) {
            Department departmentByNameCompany =
                    findDepartmentByNameCompany(update.getNameCompany());
            if (departmentByNameCompany == null) {
                entityManager.merge(update);
            } else if (departmentByNameCompany.getId().equals(update.getId())) {
                departmentByNameCompany.setNameCompany(update.getNameCompany());
                departmentByNameCompany.setAddress(update.getAddress());
                departmentByNameCompany.setEmployees(update.getEmployees());
                entityManager.merge(departmentByNameCompany);
            }
        } else {
            throw new UsernameExistsException("Department don't have with id: " + update.getId());
        }
    }

    @Transactional(readOnly = true)
    public List<Employee> listEmployees(int department_id) {

        List<Employee> list = new ArrayList<>();

        try {
            Query query = entityManager
                    .createNativeQuery("select distinct id, firstname, lastname, username from employees emp, relations r where emp.id = r.employee_id and department_id != :department_id or emp.id not in (select employee_id from relations)")
                    .setParameter("department_id", department_id);

            list = query.getResultList();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Employee> findFreeEmployeesByDepartment(Long id) {
        List<Employee> employees = new ArrayList<>();

        try {
            Query query = entityManager
                    .createNativeQuery("select id, firstname, lastname, username from employees where id not in (select employee_id from relations where department_id = :department_id)", Employee.class)
                    .setParameter("department_id", id);
            employees = query.getResultList();

        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return employees;
    }

}
