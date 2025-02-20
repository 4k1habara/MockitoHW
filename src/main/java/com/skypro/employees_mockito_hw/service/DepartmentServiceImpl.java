package com.skypro.employees_mockito_hw.service;

import com.skypro.employees_mockito_hw.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String welcome() {
        return "Welcome To The Jungle!";
    }

    @Override
    public List<Employee> getEmployeesByDep(int id) {
        List<Employee> employees = new ArrayList<>(employeeService.getAllEmployees());
        return employees.stream()
                .filter(e -> e.getDepartment() == id)
                .collect(Collectors.toList());
    }

    @Override
    public int sumSalary(int id) {
        List<Employee> employees = new ArrayList<>(employeeService.getAllEmployees());
        return employees.stream()
                .filter(employee -> employee.getDepartment() == id)
                .mapToInt(e -> e.getSalary())
                .sum();
    }

    @Override
    public Employee maxSalary(int id) {
        List<Employee> employees = new ArrayList<>(employeeService.getAllEmployees());
        return employees.stream()
                .filter(employee -> employee.getDepartment() == id)
                .max(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElse(null);
    }

    @Override
    public Employee minSalary(int id) {
        List<Employee> employees = new ArrayList<>(employeeService.getAllEmployees());
        return employees.stream()
                .filter(e -> e.getDepartment() == id)
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElse(null);
    }

    @Override
    public Map<Integer, List<Employee>> allEmployeesByDeps () {
        List<Employee> employees = new ArrayList<>(employeeService.getAllEmployees());
        Map<Integer, List<Employee>> employeesByDep = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        return employeesByDep;
    }
}