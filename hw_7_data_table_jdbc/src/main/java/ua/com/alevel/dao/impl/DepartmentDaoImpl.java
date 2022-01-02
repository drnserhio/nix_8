package ua.com.alevel.dao.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.datatable.ResponseTable;
import ua.com.alevel.jdbc.DefaultDateBaseConnectSevice;
import ua.com.alevel.model.BaseUser;
import ua.com.alevel.model.impl.Department;
import ua.com.alevel.model.impl.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;
import static ua.com.alevel.constant.DataBaseConstant.*;

@Service
@AllArgsConstructor
public class DepartmentDaoImpl implements DepartmentDao {

    private final DefaultDateBaseConnectSevice connectSevice;


    @Override
    public void delete(Long id) {

    }

    @Override
    public Department findById(Long id) {
        Department department = null;
        try (PreparedStatement statement = connectSevice.getConnection().prepareStatement(FIND_DEPARTMENT_BY_ID)) {
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
        convert.setCreate(created);
        convert.setUpdate(update);
        convert.setNameCompany(nameCompany);
        convert.setAddress(address);
        return convert;

    }

    private String saveResToString(ResultSet resultSet)
            throws SQLException {
        return resultSet.getString(0);
    }

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

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public List<Department> findAll() {
        return null;
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
    public void create(Department create) {

    }

    @Override
    public void update(Department update) {

    }
}
