package alevel.dao.impl;

import alevel.dao.EmployeeDao;
import alevel.dto.EmployeeResponse;
import alevel.exception.UsernameExistsException;
import alevel.model.impl.Department;
import alevel.model.impl.Employee;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class EmployeeDaoImpl implements EmployeeDao {

    private EntityManagerFactory entityManagerFactory;

    public EmployeeDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void deleteEmployee(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager
                    .createQuery("delete from Employee as employee where employee.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            log.debug(e.getMessage());
        } finally {
            entityManager.close();
        }

    }

    @Override
    public Employee findEmployeeById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Employee employee = null;
        try {
           employee = entityManager.find(Employee.class, id);
        } catch (Exception e) {
            entityManager.close();
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public boolean existsById(Long id) {
        Employee byId = findEmployeeById(id);
        if (byId == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean existsByUsername(String username) {
        Employee employeeByUsername = findEmployeeByUsername(username);
        if (employeeByUsername == null) {
            return false;
        }
        return true;
    }

    @Override
    public Employee findEmployeeByUsername(String username) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Employee employee = null;
        try {
            Query query = entityManager
                    .createQuery("select  emp from Employee emp where emp.username = :username", Employee.class)
                    .setParameter("username", username);

            List<Employee> resultList = query.getResultList();
            employee = resultList.get(0);

        } catch (Exception e) {
            entityManager.close();
            log.debug(e.getMessage());
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> employees = new ArrayList<>();
        try {
            Query query = entityManager
                    .createQuery("select emp from Employee emp");
            employees = (List<Employee>) query.getResultList();
        } catch (Exception e) {
            entityManager.close();
            e.printStackTrace();
        }
        return  employees;
    }

    public int countEmployeeEntity() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        int count = 0;
        try {
            Query query = entityManager
                    .createQuery("select (id) from Employee ");
            count = query.getResultList().size();
        } catch (Exception e) {
            entityManager.close();
            log.debug(e.getMessage());
        }
        return count;
    }

    @Override
    public EmployeeResponse findAllWithSortColumn(int page, int showEntity, String column, String sort) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> list = new ArrayList<>();
        int firstPage = (page - 1) * showEntity;
        log.debug(sort);

        try {
            String sql = String.format("select * from employees order by %s %s limit %d, %d", column, sort, firstPage, showEntity);
            Query query = entityManager
                    .createNativeQuery(sql, Employee.class);
            list = query.getResultList();
        } catch (Exception e) {
            entityManager.close();
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
    public void createEmployee(Employee create) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        if (existsByUsername(create.getUsername())) {
            throw new UsernameExistsException("Username already taken: " + create.getUsername());
        }
        try {
            transaction.begin();
            entityManager.persist(create);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            log.debug(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateEmployee(Employee update) {

        if (existsById(update.getId())) {
            Employee employee = findEmployeeByUsername(update.getUsername());
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                if (employee == null) {
                    transaction.begin();
                    entityManager.merge(update);
                    transaction.commit();
                } else if (employee.getId().equals(update.getId())) {
                    employee.setFirstname(update.getFirstname());
                    employee.setLastname(update.getLastname());
                    employee.setUsername(update.getUsername());
                    employee.setDepartments(update.getDepartments());
                    transaction.begin();
                    entityManager.merge(employee);
                    transaction.commit();
                }
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            } finally {
                entityManager.close();
            }
        } else {
            throw new UsernameExistsException("Employee don't have with id: " + update.getId());
        }
    }

    @Override
    public void addDepartmentForEmployee(Long employee_id, Long department_id) {
        if (findEmployeeById(employee_id) == null) {
            throw new RuntimeException("Employee don't find with id: " + employee_id);
        }
        if (findDepartmentById(department_id) == null) {
            throw new RuntimeException("Department don't find with id: " + department_id);
        }
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager
                    .createNativeQuery("insert into relations values (:dep_id, :emp_id)")
                    .setParameter("dep_id", department_id)
                    .setParameter("emp_id", employee_id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            log.debug(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    public Department findDepartmentById(Long department_id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Department department = null;
        try  {
          department = entityManager.find(Department.class, department_id);
        } catch (Exception e) {
            entityManager.close();
            log.debug(e.getMessage());
        }
        return department;
    }

    @Override
    public void deleteDepartmentForEmployee(Long department_id, Long employee_id) {
        if (findEmployeeById(employee_id) == null) {
            throw new RuntimeException("Employee don't find with id: " + employee_id);
        }
        if (findDepartmentById(department_id) == null) {
            throw new RuntimeException("Department don't find with id: " + department_id);
        }
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager
                    .createNativeQuery("delete from relations where department_id = :dep_id and employee_id = :emp_id")
                    .setParameter("dep_id", department_id)
                    .setParameter("emp_id", employee_id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            log.debug(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Department> findDepartmentsByEmployee(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Department> departments = new ArrayList<>();
        try {
            Query query = entityManager
                    .createNativeQuery("select id, address, nameCompany from departments where departments.id in (select department_id from relations where employee_id = :id)", Department.class)
                    .setParameter("id", id);
            departments = query.getResultList();
        } catch (Exception e) {
            entityManager.close();
            log.debug(e.getMessage());
        }
        return departments;
    }

    public List<Department> findFreeDepartmentByEmployee(Long employeeId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Department> departments = new ArrayList<>();

        try {
            Query query = entityManager
                    .createNativeQuery("select id, address, nameCompany from departments where id not in (select department_id from relations where employee_id = :emp_id)", Department.class)
                    .setParameter("emp_id", employeeId);
            departments = query.getResultList();
        } catch (Exception e) {
            entityManager.close();
            log.debug(e.getMessage());
        }
        return departments;
    }


}
