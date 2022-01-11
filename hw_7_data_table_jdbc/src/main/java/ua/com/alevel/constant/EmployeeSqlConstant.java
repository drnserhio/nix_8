package ua.com.alevel.constant;

public class EmployeeSqlConstant {


    public static final String CREATE_EMPLOYEE = "insert into employee values(default, ?, ?, ?, ?, ?)";
    public static final String UPDATE_EMPLOYEE = "update employee set dateUpdate=?, firstname=?, lastname=?, username=? where id=";
    public static final String DELETE_EMPLOYEE = "delete from employee where id = ";
    public static final String FIND_EMPLOYEE_BY_ID = "select * from employee where id =";
    public static final String FIND_EMPLOYEE_BY_USERNAME = "select * from employee where username ='%s'";
    public static final String FIND_ALL_EMPLOYEE = "select * from employee";
    public static final String FIND_ALL_DEPARTMENTS_BY_EMPLOYEE_ID = "select * from relations where employee_id = ";
    public static final String ADD_DEPARTMENT_FOR_EMPLOYEE = "insert into relations values (%d, %d)";
    public static final String DELETE_DEPARTMENT_FOR_EMPLOYEE = "delete from relations where department_id= %d and employee_id = %d";

    public static final String FIND_ALL_SQL_LIMIT = "select * from employee limit ";
    public static final String FIND_ALL_SQL_LIMIT_WITH_SORT = "select * from employee order by %s %s limit ";


}
