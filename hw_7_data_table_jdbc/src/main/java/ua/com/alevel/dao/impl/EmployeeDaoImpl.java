package ua.com.alevel.dao.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.datatable.ResponseTable;
import ua.com.alevel.exception.UsernameExistsException;
import ua.com.alevel.jdbc.DefaultDateBaseConnectSevice;
import ua.com.alevel.model.impl.Department;
import ua.com.alevel.model.impl.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;
import static ua.com.alevel.constant.DataBaseConstant.*;

@Service
@AllArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {

    private final DefaultDateBaseConnectSevice connectSevice;
    private final DepartmentDaoImpl departmentDao;


    @Override
    public void delete(Long id) {
        try (Statement statement = connectSevice.getConnection().createStatement()) {
            statement.executeUpdate(DELETE_EMPLOYEE);
        } catch (SQLException throwables) {
            out.println("e: " + throwables.getMessage());
        }
    }

    @Override
    public Employee findById(Long id) {
        Employee employee = null;
        try (PreparedStatement statement = connectSevice.getConnection().prepareStatement(FIND_EMPLOYEE_BY_ID)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employee = convertResultToEmployee(resultSet);
            }
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
        return employee;
    }

    @Override
    public boolean existsById(Long id) {
        Employee byId = findById(id);
        if (byId == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean existsByUsername(String username) {
        Employee employeeByUsername = findEmployeeByUsername(username);
        if (employeeByUsername == null) {
            return true;
        }
        return false;
    }

    @Override
    public Employee findEmployeeByUsername(String username) {
        Employee employee = null;
        try (PreparedStatement statement = connectSevice.getConnection().prepareStatement(String.format(FIND_EMPLOYEE_BY_USERNAME, username))) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employee = convertResultToEmployee(resultSet);
            }
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        try (Statement statement = connectSevice.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_EMPLOYEE);
            while (resultSet.next()) {
                list.add(convertResultToEmployee(resultSet));
            }
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
        return list;
    }

    public Employee convertResultToEmployee(ResultSet resultSet)
            throws SQLException {
        long id = resultSet.getLong(1);
        Timestamp created = resultSet.getTimestamp(2);
        Timestamp update = resultSet.getTimestamp(3);
        String firstname = resultSet.getString(4);
        String lastname = resultSet.getString(5);
        String username = resultSet.getString(6);
        Employee convert = new Employee();
        convert.setId(id);
        convert.setCreate(created);
        convert.setUpdate(update);
        convert.setFirstname(firstname);
        convert.setLastname(lastname);
        convert.setUsername(username);
        return convert;
    }

    @Override
    public ResponseTable findAll(ResponseTable responseTable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Map<String, Object> findEmployeeById(Long id) {
        return null;
    }

    @Override
    public Map<String, Object> findDepartmentById(Long id) {
        return null;
    }

    @Override
    public void create(Employee create) {
        if (!existsByUsername(create.getUsername())) {
            throw new UsernameExistsException("Username already taken");
        }
        try (PreparedStatement statement = connectSevice.getConnection().prepareStatement(CREATE_EMPLOYEE)) {
            statement.setTimestamp(1, new Timestamp(new Date().getTime()));
            statement.setTimestamp(2, new Timestamp(new Date().getTime()));
            statement.setString(3, create.getFirstname());
            statement.setString(4, create.getLastname());
            statement.setString(5, create.getUsername());
            statement.execute();
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
    }

    @Override
    public void update(Employee update) {
        try (PreparedStatement statement = connectSevice.getConnection().prepareStatement(UPDATE_EMPLOYEE)) {
            statement.setTimestamp(3, new Timestamp(new Date().getTime()));
            statement.setString(4, update.getFirstname());
            statement.setString(5, update.getLastname());
            statement.setString(6, update.getUsername());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addDepartmentForEmployee(Long employee_id, Long department_id) {
        Employee employee = findById(employee_id);
        if (employee == null) {
            throw new RuntimeException("Employee don't find with id: " + employee_id);
        }
        if (departmentDao.findDepartmentById(department_id) == null) {
            throw new RuntimeException("Department don't find with id: " + department_id);
        }

        try (Statement statement = connectSevice.getConnection().createStatement()) {

            statement.executeQuery("add sql for add");
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
    }

    @Override
    public void deleteDepartment(Department department) {

    }

    @Override
    public List<Department> findDepartmentsByEmployee(Long id) {
        return departmentDao.findDepartmentsByEmployee(id);
    }





}
