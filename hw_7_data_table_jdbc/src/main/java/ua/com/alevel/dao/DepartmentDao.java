package ua.com.alevel.dao;

import ua.com.alevel.datatable.ResponseTable;
import ua.com.alevel.model.BaseUser;
import ua.com.alevel.model.impl.Department;
import ua.com.alevel.model.impl.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentDao <E extends BaseUser> {
    Map<String , Object> findEmployeeById(Long id);
    Map<String, Object> findDepartmentById(Long id);

    void create(Department create);
    void update(Department update);
    void delete(Long id);
    E findById(Long id);
    boolean existsById(Long id);


    List<Department> findAll();

    ResponseTable<E> findAll(ResponseTable responseTable);
    long count();

}

