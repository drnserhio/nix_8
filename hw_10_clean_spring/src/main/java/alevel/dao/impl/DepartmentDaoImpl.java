package alevel.dao.impl;

import alevel.dao.DepartmentDao;
import alevel.dto.DepartmentResponse;
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

import static java.lang.System.out;


@Slf4j
public class DepartmentDaoImpl implements DepartmentDao {

    private EntityManagerFactory entityManagerFactory;

    public DepartmentDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void deleteDepartment(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.createQuery("delete from Department as department where department.id = :id")
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
    public Department findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Department department = null;
        try {
           department =  entityManager.find(Department.class, id);
        } catch (Exception e) {
            entityManager.close();
            e.printStackTrace();
        }
        return department;
    }


    @Override
    public boolean existsDepartmentById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        boolean isExists = false;
        try {
            Query query = entityManager.createQuery("select count(id) from Department where id = :id").setParameter("id", id);
            isExists =  query.getResultList().size() == 1;
        } catch (Exception e) {
            entityManager.close();
            e.printStackTrace();
        }
        return isExists;
    }

    @Override
    public List<Department> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Department> departments = new ArrayList<>();
        try {
            Query query = entityManager
                    .createQuery("select dep from Department dep");
            departments = (List<Department>) query.getResultList();
        } catch (Exception e) {
            entityManager.close();
            e.printStackTrace();
        }
        return departments;
    }


    @Override
    public void addEmployeeForDepartment(Long department_id, Long employee_id) {
        if (findById(department_id) == null) {
            throw new RuntimeException("Department don't find with id: " + department_id);
        }
        if (findEmployeeById(employee_id) == null) {
            throw new RuntimeException("Employee don't find with id: " + employee_id);
        }
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager
                    .createNativeQuery("insert into relations values (:dep_id, :emp_id)")
                    .setParameter("dep_id", department_id).setParameter("emp_id", employee_id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            log.debug(e.getMessage());
        } finally {
            entityManager.close();
        }

    }

    public Employee findEmployeeById(Long employee_id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Employee employee = null;
        try {
            employee = entityManager.find(Employee.class, employee_id);
        } catch (Exception e) {
            entityManager.close();
            out.println(e.getMessage());
        }
        return employee;
    }


    @Override
    public void deleteEmployeeForDepartment(Long department_id, Long employee_id) {
        if (findById(department_id) == null) {
            throw new RuntimeException("Department don't find with id: " + department_id);
        }
        if (findEmployeeById(employee_id) == null) {
            throw new RuntimeException("Employee don't find with id: " + employee_id);
        }
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager
                    .createNativeQuery("delete  from relations where department_id= :dep_id and employee_id = :emp_id")
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
    public List<Employee> findEmployeesByDepartment(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> employees = new ArrayList<>();
        try {
            Query query = entityManager
                    .createNativeQuery("select id, firstname, lastname, username  from employees  where employees.id  in (select employee_id from relations where department_id = :id)", Employee.class)
                    .setParameter("id", id);
            employees = query.getResultList();
        } catch (Exception e) {
            entityManager.close();
            log.debug(e.getMessage());
        }
        return employees;
    }

    public int countDepartmentEntity() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        int count = 0;
        try {
            Query query = entityManager
                    .createQuery("select (id) from Department ");
            count = query.getResultList().size();
        } catch (Exception e) {
            entityManager.close();
            log.debug(e.getMessage());
        }
        return count;
    }


    @Override
    public DepartmentResponse findAllWithSortColumn(int page, int showEntity, String column, String sort) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Department> list = new ArrayList<>();
        int firstPage = (page - 1) * showEntity;
        try {
            String sql = String.format("select * from departments order by %s %s limit %d, %d", column, sort, firstPage, showEntity);
            Query query = entityManager
                    .createNativeQuery(sql, Department.class);
            list = query.getResultList();
        } catch (Exception e) {
            entityManager.close();
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
    public void createDepartment(Department create) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        if (existsByNameCompany(create.getNameCompany())) {
            throw new UsernameExistsException("Name company already taken");
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

    public boolean existsByNameCompany(String nameCompany) {
        if (findDepartmentByNameCompany(nameCompany) == null) {
            return false;
        }
        return true;
    }

    public Department findDepartmentByNameCompany(String nameCompany) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Department department = null;
        try {
            Query query = entityManager
                    .createQuery("select dep from Department dep where dep.nameCompany = :nameCompany")
                    .setParameter("nameCompany", nameCompany);
            List<Department> resultList = query.getResultList();
            department = resultList.get(0);

        } catch (Exception e) {
            entityManager.close();
            log.debug(e.getMessage());
        }
        return department;
    }

    @Override
    public void updateDepartment(Department update) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            if (existsDepartmentById(update.getId())) {
                Department departmentByNameCompany =
                        findDepartmentByNameCompany(update.getNameCompany());
                if (departmentByNameCompany == null) {
                    transaction.begin();
                    entityManager.merge(update);
                    transaction.commit();
                } else if (departmentByNameCompany.getId().equals(update.getId())) {
                    departmentByNameCompany.setNameCompany(update.getNameCompany());
                    departmentByNameCompany.setAddress(update.getAddress());
                    departmentByNameCompany.setEmployees(update.getEmployees());
                    transaction.begin();
                    entityManager.merge(departmentByNameCompany);
                    transaction.commit();
                }
            } else {
                throw new UsernameExistsException("Department don't have with id: " + update.getId());
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List<Employee> listEmployees(int department_id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> list = new ArrayList<>();
        try {
            Query query = entityManager
                    .createNativeQuery("select distinct id, firstname, lastname, username from employees emp, relations r where emp.id = r.employee_id and department_id != :department_id or emp.id not in (select employee_id from relations)")
                    .setParameter("department_id", department_id);
            list = query.getResultList();
        } catch (Exception e) {
            entityManager.close();
            log.debug(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Employee> findFreeEmployeesByDepartment(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> employees = new ArrayList<>();
        try {
            Query query = entityManager
                    .createNativeQuery("select id, firstname, lastname, username from employees where id not in (select employee_id from relations where department_id = :department_id)", Employee.class)
                    .setParameter("department_id", id);
            employees = query.getResultList();

        } catch (Exception e) {
            entityManager.close();
            log.debug(e.getMessage());
        }
        return employees;
    }

}
