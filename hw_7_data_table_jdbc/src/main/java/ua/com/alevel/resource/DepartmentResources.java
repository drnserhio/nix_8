package ua.com.alevel.resource;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.model.impl.Department;
import ua.com.alevel.model.impl.DepartmentResponse;
import ua.com.alevel.model.impl.Employee;
import ua.com.alevel.service.DepartmentService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/department")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class DepartmentResources {

    private final DepartmentService departmentService;

    @PostMapping("/create")
    public void create(
            @RequestBody Department create) {
        departmentService.create(create);
    }

    @PutMapping("/update")
    public void update(
            @RequestBody Department update) {
        departmentService.update(update);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable("id") Long id) {
        departmentService.delete(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Department> getDepartment(
            @PathVariable("id") Long id) {
        Department department = departmentService.findById(id);
        return new ResponseEntity<>(department, OK);
    }

    @GetMapping("/list-department")
    public ResponseEntity<List<Department>> findAllEmployee() {
        List<Department> employees = departmentService.findAll();
        return new ResponseEntity<>(employees, OK);
    }

    @GetMapping("/find/{nameCompany}")
    public ResponseEntity<Department> getEmployeeByUsername(
            @PathVariable("nameCompany") String nameCompany) {
        Department department = departmentService.findDepartmentByNameCompany(nameCompany);
        return new ResponseEntity<>(department, OK);
    }


    @GetMapping("/add/{department_id}/{employee_id}")
    public void addEmployeeForDepartment(
            @PathVariable("department_id") Long department_id,
            @PathVariable("employee_id") Long employee_id ) {
        departmentService.addDepartmentForEmployee(department_id, employee_id);
    }

    @DeleteMapping("/del/{department_id}/{employee_id}")
    public void deleteEmployeeForDepartment(
            @PathVariable("department_id") Long department_id,
            @PathVariable("employee_id") Long employee_id) {
        departmentService.deleteEmployeeForDepartment(department_id, employee_id);
    }

    @GetMapping("/get/all-employees/{id}")
    public List<Employee> findDEmployeesByDepartment(
            @PathVariable("id") Long id) {
        return departmentService.findEmployeesByDepartment(id);
    }

    @GetMapping("/limit-list/{page}/{showEntity}")
    public DepartmentResponse findAllLimit(
            @PathVariable("page") int page,
            @PathVariable( "showEntity") int showEntity) {
        return departmentService.findAllLimit(page, showEntity);
    }

    @GetMapping("/limit-list/{page}/{showEntity}/{column}/{sort}")
    public DepartmentResponse findAllWithSortColumn(
            @PathVariable("page") int page,
            @PathVariable( "showEntity") int showEntity,
            @PathVariable("column") String columnSort,
            @PathVariable("sort") String sort) {
        return departmentService.findAllWithSortColumn(page, showEntity, columnSort, sort);
    }

    @GetMapping("/list-employees/{department_id}")
    public List<Employee> listEmployees(
            @PathVariable("department_id") int department_id) {
        return departmentService.listEmployees(department_id);
    }

}
