package ua.com.alevel.dao.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.exception.UsernameExistsException;
import ua.com.alevel.jdbc.DefaultDateBaseConnectSevice;
import ua.com.alevel.model.impl.Department;
import ua.com.alevel.model.impl.Employee;
import ua.com.alevel.model.impl.EmployeeResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;
import static ua.com.alevel.constant.DepartmentSqlConstant.FIND_DEPARTMENT_BY_ID;
import static ua.com.alevel.constant.EmployeeSqlConstant.*;

@Service
@AllArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {

    private final DefaultDateBaseConnectSevice connectSevice;


    @Override
    public void deleteEmployee(Long id) {
        try (Statement statement = connectSevice.getConnection().createStatement()) {
            statement.executeUpdate(DELETE_EMPLOYEE + id);
        } catch (SQLException throwables) {
            out.println("e: " + throwables.getMessage());
        }
    }

    @Override
    public Employee findEmployeeById(Long id) {
        Employee employee = null;
        try (PreparedStatement statement = connectSevice.getConnection().prepareStatement(FIND_EMPLOYEE_BY_ID + id)) {
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

    @Override
    public EmployeeResponse findAllLimit(int page, int pageSave, int showEntity) {
        List<Employee> list = new ArrayList<>();
      try (Statement statement = connectSevice.getConnection().createStatement()){
          ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL_LIMIT + (page - 1) + "," + showEntity);
          while (resultSet.next()) {
              list.add(convertResultToEmployee(resultSet));
          }
      } catch (SQLException throwables) {
          out.println("Message: " + throwables.getMessage());
      }
      EmployeeResponse employeeResponse = new EmployeeResponse();
      employeeResponse.setEmployees(list);
      employeeResponse.setPage(pageSave);
      employeeResponse.setShowEntity(showEntity);
      employeeResponse.setCountEntity(list.size());
      employeeResponse.setAllSizeEntity(countEmployees());

      return employeeResponse;
    }

    @Override
    public EmployeeResponse findAllWithSortColumn(int page, int pageSave, int showEntity, String column, String sort) {
        List<Employee> list = new ArrayList<>();
        try (Statement statement = connectSevice.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(String.format(FIND_ALL_SQL_LIMIT_WITH_SORT, column, sort) + (page - 1) + "," + showEntity);
            while (resultSet.next()) {
                list.add(convertResultToEmployee(resultSet));
            }
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployees(list);
        employeeResponse.setPage(pageSave);
        employeeResponse.setShowEntity(showEntity);
        employeeResponse.setCountEntity(list.size());
        employeeResponse.setAllSizeEntity(countEmployees());
        employeeResponse.setSort(sort);
        return employeeResponse;
    }

    private int countEmployees() {
        return findAll().size();
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
        convert.setDateCreate(created);
        convert.setDateUpdate(update);
        convert.setFirstname(firstname);
        convert.setLastname(lastname);
        convert.setUsername(username);
        return convert;
    }

    @Override
    public void createEmployee(Employee create) {
        if (existsByUsername(create.getUsername())) {
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
    public void updateEmployee(Employee update) {
        try (PreparedStatement statement = connectSevice.getConnection().prepareStatement(UPDATE_EMPLOYEE + update.getId())) {
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
        if (findEmployeeById(employee_id) == null) {
            throw new RuntimeException("Employee don't find with id: " + employee_id);
        }
        if (findDepartmentById(department_id) == null) {
            throw new RuntimeException("Department don't find with id: " + department_id);
        }

        try (Statement statement = connectSevice.getConnection().createStatement()) {
            statement.executeUpdate(String.format(ADD_DEPARTMENT_FOR_EMPLOYEE, department_id, employee_id));
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
    }

    private Department findDepartmentById(Long department_id) {
        Department department = null;
        try (PreparedStatement statement = connectSevice.getConnection().prepareStatement(FIND_DEPARTMENT_BY_ID + department_id)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                department = convertResultToDepartment(resultSet);
            }
        } catch (SQLException throwables) {
            out.println("Message: " + throwables.getMessage());
        }
        return department;
    }

    private Department convertResultToDepartment(ResultSet resultSet) throws SQLException {
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
    public void deleteDepartment(Long department_id, Long employee_id) {
        if (findEmployeeById(employee_id) == null) {
            throw new RuntimeException("Employee don't find with id: " + employee_id);
        }
        if (findDepartmentById(department_id) == null) {
            throw new RuntimeException("Department don't find with id: " + department_id);
        }

        try (Statement statement = connectSevice.getConnection().createStatement()) {
            statement.executeUpdate(String.format(DELETE_DEPARTMENT_FOR_EMPLOYEE, department_id, employee_id));
        } catch (SQLException throwables) {
            Department department = findDepartmentById(department_id);
            Employee employee = findEmployeeById(employee_id);
            throw new RuntimeException("Employee : " + employee.getUsername() + " dont't work in department - " + department.getNameCompany());
        }

    }

    @Override
    public List<Department> findDepartmentsByEmployee(Long id) {
        List<String> departments = new ArrayList<>();
        try (Statement statement = connectSevice.getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery(FIND_ALL_DEPARTMENTS_BY_EMPLOYEE_ID + id);

            while (resultSet.next()) {
                departments.add(saveResToString(resultSet));
            }
        } catch (SQLException throwables) {
            out.println("e: " + throwables);
        }
        if (departments.size() < 1) {
            throw new RuntimeException("Employee don't have any department");
        }
        List<Department> department = convertResultToDepartments(departments);
        return department;
    }


    private String saveResToString(ResultSet resultSet)
            throws SQLException {
        return resultSet.getString(1);
    }

    public List<Department> convertResultToDepartments(List<String> list) {

        List<Department> departments = new ArrayList<>();
        for (String id : list) {
            try (Statement statement = connectSevice.getConnection().createStatement()) {

                ResultSet resultSet = statement.executeQuery(FIND_DEPARTMENT_BY_ID + Long.parseLong(id));

                if (resultSet.next()) {
                    departments.add(convertToDepartment(resultSet));
                }
            } catch (SQLException throwables) {
                out.println("e: " + throwables);
            }
        }
        return departments;
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





}
