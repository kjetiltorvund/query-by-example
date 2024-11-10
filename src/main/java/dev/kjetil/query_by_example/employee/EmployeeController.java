package dev.kjetil.query_by_example.employee;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String department
    ) {
        var employees = employeeService.findEmployeesWithCustomMatcher(firstName, department);
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/search/example")
    public List<Employee> findByExample(@RequestBody @Valid Employee employee) {
        return employeeService.findEmployeesByExample(employee);
    }
}
