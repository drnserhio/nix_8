package ua.com.alevel.constant;

public class DepartmentSqlConstant {

    public static final String CREATE_DEPARTMENT = "insert into department values(default, ?, ?, ?, ?)";
    public static final String UPDATE_DEPARTMENT = "update from department set id = ";
    public static final String DELETE_DEPARTMENT = "delete from department where id =";
    public static final String FIND_DEPARTMENT_BY_ID = "select * from department where id =";
    public static final String FIND_DEPARTMENT_BY_NAME_COMPANY = "select * from department where nameCompany = '%s'";
    public static final String FIND_ALL_DEPARTMENT = "select * from department";
    public static final String FIND_ALL_EMPLOYEE_BY_DEPARTMENT_ID = "select * from relations where department_id = ";
    public static final String ADD_EMPLOYEE_FOR_DEPARTMENT = "insert into relations values (%d, %d)";
    public static final String DELETE_EMPLOYEE_FOR_DEPARTMENT = "delete from relations where department_id= %d and employee_id = %d";


    public static final String FIND_EMPLOYEE_BY_DEPARTMENT = "select id, firstname, lastname from employee left join relations ed on employee.id = ed.id where employee_id = ";
}
