package ua.com.alevel.constant;

public class DataBaseConstant {

    public static final String CREATE_DEPARTMENT = "insert into department values(default, ?, ?)";

    public static final String CREATE_EMPLOYEE = "insert into employee values(default, ?, ?, ?, ?, ?)";
    public static final String UPDATE_EMPLOYEE = "update employee set dateUpdate=?, firstname=?, lastname=?  where id=?";
    public static final String DELETE_EMPLOYEE = "delete employee where id = ?";
    public static final String FIND_EMPLOYEE_BY_ID = "select * from employee where id =";
    public static final String FIND_EMPLOYEE_BY_USERNAME = "select * from employee where username ='%s'";
    public static final String FIND_ALL_EMPLOYEE = "select * from employee";
    public static final String FIND_ALL_DEPARTMENTS_BY_EMPLOYEE_ID = "select * from relations where employee_id = ";



    public static final String FIND_ALL_EMPLOYEE_BY_ID = "select s.id, s.create, s.update, s.firstname, s.lastname, count(employee_id) as count_department from as s left join relations as cd on s.id = cd.id group by s.id";



    public static final String UPDATE_DEPARTMENT = "update department set id = ";
    public static final String FIND_DEPARTMENT_BY_ID = "select * from department where id =";
    public static final String FIND_EMPLOYEE_BY_DEPARTMENT = "select id, firstname, lastname from employee left join relations ed on employee.id = ed.id where employee_id = ";

}
