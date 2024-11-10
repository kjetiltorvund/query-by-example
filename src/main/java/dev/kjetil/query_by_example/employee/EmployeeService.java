package dev.kjetil.query_by_example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.*;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Find all employees matching exact criteria
    // Find all employees with the values given in the Employee object.
    public List<Employee> findEmployeesByExample(Employee employee) {
        Example<Employee> example = Example.of(employee);
        return employeeRepository.findAll(example);
    }

    // Find a single employee
    public Optional<Employee> findOneEmployeeByExample(Employee employee) {
        Example<Employee> example = Example.of(employee);
        return employeeRepository.findOne(example);
    }

    // Find employee with custom mapper, which will ignore case, use contains string in search, ignore null values and do exact match on first name.
    public List<Employee> findEmployeesWithCustomMatcher(String firstName, String department) {
        Employee employee = Employee.builder()
                .firstName(firstName)
                .department(department)
                .build();

        ExampleMatcher matcher = matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING) // Use LIKE %value% for strings
                .withIgnoreNullValues() // Ignore null values
                .withMatcher("firstName", GenericPropertyMatcher::exact)
                .withMatcher("department", GenericPropertyMatcher::contains);

        Example<Employee> example = Example.of(employee, matcher);
        return employeeRepository.findAll(example);
    }

    public long countEmployeesByExample(Employee employee) {
        Example<Employee> example = Example.of(employee);
        return employeeRepository.count(example);
    }

    public boolean existsByExample(Employee employee) {
        Example<Employee> example = Example.of(employee);
        return employeeRepository.exists(example);
    }
}
