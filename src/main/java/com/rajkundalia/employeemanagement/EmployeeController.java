package com.rajkundalia.employeemanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/add")
    public String addEmployees() {
        Employee emp1 = new Employee();
        emp1.setName("Alice Smith");
        emp1.setEmail("alice.smith@example.com");
        emp1.setRole("Manager");

        Employee emp2 = new Employee();
        emp2.setName("Bob Johnson");
        emp2.setEmail("bob.johnson@example.com");
        emp2.setRole("Developer");

        Employee emp3 = new Employee();
        emp3.setName("Charlie Brown");
        emp3.setEmail("charlie.brown@example.com");
        emp3.setRole("Designer");

        // Save employees to the database
        employeeService.addEmployees(Arrays.asList(emp1, emp2, emp3));

        return "created";
    }
}
