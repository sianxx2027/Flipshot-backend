package com.example.flipshot;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "https://flipshot-opal.vercel.app")
public class MyController {

    private final EmployeeService employeeService;

    public MyController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeResponse addEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        return employeeService.addEmployee(request);
    }

    @GetMapping
    public List<EmployeeResponse> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeByID(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody EmployeeRequest request) {

        return employeeService.updateEmployee(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployeeByID(@PathVariable int id) {

        boolean deleted = employeeService.deleteEmployeeById(id);

        if (deleted) {
            return "Employee deleted successfully";
        }

        return "Employee not found";
    }

    @DeleteMapping
    public String deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return "All employees deleted successfully";
    }
}