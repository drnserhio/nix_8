package ua.com.alevel.resource;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.model.BaseUser;
import ua.com.alevel.model.impl.Employee;
import ua.com.alevel.service.EmployeeService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin("http://locahost:4200")
@AllArgsConstructor
@RequestMapping(value = {"/", "/employee"})
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
    public ResponseEntity<Employee> getEmployee(Long id) {
        Employee byId = employeeService.findById(id);
        return new ResponseEntity<>(byId, OK);
    }

    @GetMapping("/list-employee")
    public ResponseEntity<List<Employee>> findAllEmployee() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(employees, OK);
    }

    @PostMapping("/find/{username}")
    public ResponseEntity<Employee> getEmployeeByUsername(
            @PathVariable("username") String username) {
        Employee employee = employeeService.findByUsername(username);
        return new ResponseEntity<>(employee, OK);
    }




}
