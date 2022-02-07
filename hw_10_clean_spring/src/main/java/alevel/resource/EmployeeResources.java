package alevel.resource;

import alevel.dto.EmployeeResponse;
import alevel.model.impl.Department;
import alevel.model.impl.Employee;
import alevel.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = {"/", "/employee"})
@CrossOrigin("http://localhost:4200")
public class EmployeeResources {
    private final EmployeeService employeeService;

    public EmployeeResources(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(
            @RequestBody Employee create) {
        employeeService.createEmployee(create);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(
            @RequestBody Employee update) {
        employeeService.updateEmployee(update);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(
            @PathVariable("id") Long id) {
        Employee employee = employeeService.findById(id);
        return new ResponseEntity<>(employee, OK);
    }

    @GetMapping(value = "/list-employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> findAllEmployee() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(employees, OK);
    }

    @GetMapping(value = "/find/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployeeByUsername(
            @PathVariable("username") String username) {
        Employee employee = employeeService.findByUsername(username);
        return new ResponseEntity<>(employee, OK);
    }

    @GetMapping(value = "/add/{department_id}/{employee_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addDepartmentForEmployee(
            @PathVariable("employee_id") Long employee_id,
            @PathVariable("department_id") Long department_id) {
        employeeService.addDepartmentForEmployee(employee_id, department_id);
    }

    @DeleteMapping(value = "/del/{department_id}/{employee_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteDepartment(
            @PathVariable("department_id") Long department_id,
            @PathVariable("employee_id") Long employee_id) {
        employeeService.deleteDepartment(department_id, employee_id);
    }

    @GetMapping(value = "/get/all-departments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Department> findDepartmentsByEmployee(
            @PathVariable("id") Long id) {
        return employeeService.findDepartmentsByEmployee(id);
    }

    @GetMapping(value = "/limit-list/{page}/{showEntity}/{column}/{sort}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeResponse findAllWithSortColumn(
            @PathVariable("page") int page,
            @PathVariable( "showEntity") int showEntity,
            @PathVariable("column") String columnSort,
            @PathVariable("sort") String sort) {
        return employeeService.findAllWithSortColumn(page, showEntity, columnSort, sort);
    }

    @GetMapping(value = "/free-departments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Department> findFreeDepartmentByEmployee(
            @PathVariable("id") Long id) {
        return employeeService.findFreeDepartmentByEmployee(id);
    }


}
