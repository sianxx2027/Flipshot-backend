package com.example.flipshot;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponse addEmployee(EmployeeRequest request) {

        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setAge(request.getAge());
        employee.setSalary(request.getSalary());
        employee.setDesig(request.getDesig());

        return convertToResponse(employeeRepository.save(employee));
    }

    public List<EmployeeResponse> getEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public EmployeeResponse getEmployeeById(int id) {

        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            return null;
        }

        return convertToResponse(employee);
    }

    public EmployeeResponse updateEmployee(int id, EmployeeRequest request) {

        return employeeRepository.findById(id)
                .map(employee -> {

                    employee.setName(request.getName());
                    employee.setAge(request.getAge());
                    employee.setSalary(request.getSalary());
                    employee.setDesig(request.getDesig());

                    return convertToResponse(employeeRepository.save(employee));
                })
                .orElse(null);
    }

    public boolean deleteEmployeeById(int id) {

        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    private EmployeeResponse convertToResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();

        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setAge(employee.getAge());
        response.setSalary(employee.getSalary());
        response.setDesig(employee.getDesig());

        return response;
    }
}