package ua.com.alevel.resource;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.model.impl.Department;
import ua.com.alevel.model.impl.Employee;
import ua.com.alevel.model.impl.EmployeeResponse;
import ua.com.alevel.service.EmployeeService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping(value = {"/", "/employee"})
@CrossOrigin("http://localhost:4200")
public class EmployeeResources {
    private final EmployeeService employeeService;

    @PostMapping("/create")
    public void create(
            @RequestBody Employee create) {
        employeeService.createEmployee(create);
    }

    @PutMapping("/update")
    public void update(
            @RequestBody Employee update) {
        employeeService.updateEmployee(update);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployee(
            @PathVariable("id") Long id) {
        Employee employee = employeeService.findById(id);
        return new ResponseEntity<>(employee, OK);
    }

    @GetMapping("/list-employee")
    public ResponseEntity<List<Employee>> findAllEmployee() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(employees, OK);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<Employee> getEmployeeByUsername(
            @PathVariable("username") String username) {
        Employee employee = employeeService.findByUsername(username);
        return new ResponseEntity<>(employee, OK);
    }


    @PostMapping("/add/{department_id}/{employee_id}")
    public void addDepartmentForEmployee(
            @PathVariable("employee_id") Long employee_id,
            @PathVariable("department_id") Long department_id) {
        employeeService.addDepartmentForEmployee(employee_id, department_id);
    }

    @DeleteMapping("/del/{department_id}/{employee_id}")
    public void deleteDepartment(
            @PathVariable("department_id") Long department_id,
            @PathVariable("employee_id") Long employee_id) {
        employeeService.deleteDepartment(department_id, employee_id);
    }

    @GetMapping("/get/all-departments/{id}")
    public List<Department> findDepartmentsByEmployee(
            @PathVariable("id") Long id) {
        return employeeService.findDepartmentsByEmployee(id);
    }

    @GetMapping("/limit-list/{page}/{showEntity}")
    public EmployeeResponse findAllLimit(
            @PathVariable("page") int page,
            @PathVariable( "showEntity") int showEntity) {
        return employeeService.findAllLimit(page, showEntity);
    }

    @GetMapping("/limit-list/{page}/{showEntity}/{column}/{sort}")
    public EmployeeResponse findAllWithSortColumn(
            @PathVariable("page") int page,
            @PathVariable( "showEntity") int showEntity,
            @PathVariable("column") String columnSort,
            @PathVariable("sort") String sort) {
        return employeeService.findAllWithSortColumn(page, showEntity, columnSort, sort);
    }





}
