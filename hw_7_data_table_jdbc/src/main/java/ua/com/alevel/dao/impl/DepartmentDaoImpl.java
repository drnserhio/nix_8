package ua.com.alevel.dao.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.exception.UsernameExistsException;
import ua.com.alevel.jdbc.DefaultDateBaseConnectSevice;
import ua.com.alevel.model.impl.Department;
import ua.com.alevel.model.impl.DepartmentResponse;
import ua.com.alevel.model.impl.Employee;
import ua.com.alevel.model.impl.EmployeeResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;
import static ua.com.alevel.constant.DepartmentSqlConstant.*;
import static ua.com.alevel.constant.EmployeeSqlConstant.*;
import static ua.com.alevel.constant.EmployeeSqlConstant.FIND_ALL_SQL_LIMIT;

@Service
@AllArgsConstructor
public class DepartmentDaoImpl implements DepartmentDao {

    private final DefaultDateBaseConnectSevice connectSevice;


    @Override
    public void deleteDepartment(Long id) {
        try (Statement statement = connectSevice.getConnection().createStatement()) {
            statement.executeUpdate(DELETE_DEPARTMENT + id);
        } catch (SQLException throwables) {
            out.println("e: " + throwables.getMessage());
        }

    }

    @Override
    public Department findById(Long id) {
        Department department = null;
        try (PreparedStatement statement = connectSevice.getConnection().prepareStatement(FIND_DEPARTMENT_BY_ID + id)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                department = convertToDepartment(resultSet);
            }
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
        return department;
    }

    private Department convertToDepartment(ResultSet resultSet)
            throws SQLException {
        long id = resultSet.getLong(1);
        Timestamp created = resultSet.getTimestamp(2);
        Timestamp update = resultSet.getTimestamp(3);
        String nameCompany = resultSet.getString(4);
        String address = resultSet.getString(5);

        Department convert = new Department();
        convert.setId(id);
        convert.setDateCreate(created);
        convert.setDateUpdate(update);
        convert.setNameCompany(nameCompany);
        convert.setAddress(address);
        return convert;

    }


    @Override
    public boolean existsDepartmentById(Long id) {
        return false;
    }

    @Override
    public List<Department> findAll() {
        List<Department> list = new ArrayList<>();
        try (Statement statement = connectSevice.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_DEPARTMENT);
            while (resultSet.next()) {
                list.add(convertToDepartment(resultSet));
            }
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
        return list;
    }

    @Override
    public void addEmployeeForDepartment(Long department_id, Long employee_id) {
        if (findById(department_id) == null) {
            throw new RuntimeException("Department don't find with id: " + department_id);
        }
        if (findEmployeeById(employee_id) == null) {
            throw new RuntimeException("Employee don't find with id: " + employee_id);
        }

        try (Statement statement = connectSevice.getConnection().createStatement()) {
            statement.executeUpdate(String.format(ADD_EMPLOYEE_FOR_DEPARTMENT, department_id, employee_id));
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
    }

    private Employee findEmployeeById(Long employee_id) {
        Employee employee = null;
        try (PreparedStatement statement = connectSevice.getConnection().prepareStatement(FIND_EMPLOYEE_BY_ID + employee_id)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employee = convertResultToEmployee(resultSet);
            }
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
        return employee;
    }

    private Employee convertResultToEmployee(ResultSet resultSet)
            throws SQLException {
        long id = resultSet.getLong(1);
        Timestamp created = resultSet.getTimestamp(2);
        Timestamp update = resultSet.getTimestamp(3);
        String firstname = resultSet.getString(4);
        String lastname = resultSet.getString(5);
        String username = resultSet.getString(6);
        Employee convert = new Employee();
        convert.setId(id);
        convert.setDateCreate(created);
        convert.setDateUpdate(update);
        convert.setFirstname(firstname);
        convert.setLastname(lastname);
        convert.setUsername(username);
        return convert;
    }

    @Override
    public void deleteEmployeeForDepartment(Long department_id, Long employee_id) {
        if (findById(department_id) == null) {
            throw new RuntimeException("Department don't find with id: " + department_id);
        }
        if (findEmployeeById(employee_id) == null) {
            throw new RuntimeException("Employee don't find with id: " + employee_id);
        }

        try (Statement statement = connectSevice.getConnection().createStatement()) {
            statement.executeUpdate(String.format(DELETE_EMPLOYEE_FOR_DEPARTMENT, department_id, employee_id));
        } catch (SQLException throwables) {
            Employee employee = findEmployeeById(department_id);
            Department department = findById(employee_id);
            throw new RuntimeException("Employee : " + employee.getUsername() + " dont't work in department - " + department.getNameCompany());
        }
    }

    @Override
    public List<Employee> findEmployeesByDepartment(Long id) {
        List<String> employees = new ArrayList<>();
        try (Statement statement = connectSevice.getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery(FIND_ALL_EMPLOYEE_BY_DEPARTMENT_ID + id);

            while (resultSet.next()) {
                employees.add(saveToString(resultSet));
            }
        } catch (SQLException throwables) {
            out.println("e: " + throwables);
        }
        if (employees.size() < 1) {
            throw new RuntimeException("Department don't have any employee");
        }
        List<Employee> department = convertResultToEmployees(employees);
        return department;
    }

    @Override
    public DepartmentResponse findAllLimit(int page, int pageSave, int showEntity) {
        List<Department> list = new ArrayList<>();
        try (Statement statement = connectSevice.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL_LIMIT_DEPARTMENT + (page - 1) + "," + showEntity);
            while (resultSet.next()) {
                list.add(convertToDepartment(resultSet));
            }
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setDepartments(list);
        departmentResponse.setPage(pageSave);
        departmentResponse.setShowEntity(showEntity);
        departmentResponse.setCountEntity(list.size());
        departmentResponse.setAllSizeEntity(countDepartments());
        return departmentResponse;
    }

    @Override
    public DepartmentResponse findAllWithSortColumn(int page, int pageSave, int showEntity, String column, String sort) {
        List<Department> list = new ArrayList<>();
        try (Statement statement = connectSevice.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(String.format(FIND_ALL_SQL_LIMIT_WITH_SORT_DEPARTMENT, column, sort) + (page - 1) + "," + showEntity);
            while (resultSet.next()) {
                list.add(convertToDepartment(resultSet));
            }
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setDepartments(list);
        departmentResponse.setPage(pageSave);
        departmentResponse.setShowEntity(showEntity);
        departmentResponse.setCountEntity(list.size());
        departmentResponse.setAllSizeEntity(countDepartments());
        departmentResponse.setSort(sort);
        return departmentResponse;
    }

    private List<Employee> convertResultToEmployees(List<String> employees) {
        List<Employee> em = new ArrayList<>();
        for (String id : employees) {
            try (Statement statement = connectSevice.getConnection().createStatement()) {

                ResultSet resultSet = statement.executeQuery(FIND_EMPLOYEE_BY_ID + Long.parseLong(id));

                if (resultSet.next()) {
                    em.add(convertToEmployee(resultSet));
                }
            } catch (SQLException throwables) {
                out.println("e: " + throwables);
            }
        }
        return em;
    }

    private Employee convertToEmployee(ResultSet resultSet)
            throws SQLException {
        long id = resultSet.getLong(1);
        Timestamp created = resultSet.getTimestamp(2);
        Timestamp update = resultSet.getTimestamp(3);
        String firstname = resultSet.getString(4);
        String lastname = resultSet.getString(5);
        String username = resultSet.getString(6);
        Employee convert = new Employee();
        convert.setId(id);
        convert.setDateCreate(created);
        convert.setDateUpdate(update);
        convert.setFirstname(firstname);
        convert.setLastname(lastname);
        convert.setUsername(username);
        return convert;
    }


    private String saveToString(ResultSet resultSet)
            throws SQLException {
        return resultSet.getString(2);
    }

    @Override
    public void createDepartment(Department create) {

        if (existsByNameCompany(create.getNameCompany())) {
            throw new UsernameExistsException("Name company already taken");
        }
        try (PreparedStatement statement = connectSevice.getConnection().prepareStatement(CREATE_DEPARTMENT)) {
            statement.setTimestamp(1, new Timestamp(new Date().getTime()));
            statement.setTimestamp(2, new Timestamp(new Date().getTime()));
            statement.setString(3, create.getNameCompany());
            statement.setString(4, create.getAddress());
            statement.execute();
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
    }

    private boolean existsByNameCompany(String nameCompany) {
        if (findDepartmentByNameCompany(nameCompany) == null) {
            return false;
        }
        return true;
    }

    public Department findDepartmentByNameCompany(String nameCompany) {
        Department department = null;
        try (PreparedStatement statement = connectSevice.getConnection().prepareStatement(String.format(FIND_DEPARTMENT_BY_NAME_COMPANY, nameCompany))) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                department = convertToDepartment(resultSet);
            }
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
        return department;
    }

    @Override
    public void updateDepartment(Department update) {
        try (PreparedStatement statement = connectSevice.getConnection().prepareStatement(UPDATE_DEPARTMENT)) {
            statement.setTimestamp(2, new Timestamp(new Date().getTime()));
            statement.setString(3, update.getNameCompany());
            statement.setString(4, update.getNameCompany());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private int countDepartments() {
        return findAll().size();
    }


}
