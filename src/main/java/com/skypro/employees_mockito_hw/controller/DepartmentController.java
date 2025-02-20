package com.skypro.employees_mockito_hw.controller;

import com.skypro.employees_mockito_hw.Employee;
import com.skypro.employees_mockito_hw.service.DepartmentService;
import com.skypro.employees_mockito_hw.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping("{id:\\d+}/employees")
    public List<Employee> getEmployeesByDep(@PathVariable int id) {
        return departmentService.getEmployeesByDep(id);
    }

    @GetMapping("{id:\\d+}/salary/sum")
    public int sumSalary(@PathVariable int id) {
        return departmentService.sumSalary(id);
    }

    @GetMapping("{id:\\d+}/salary/max")
    public Employee maxSalary(@PathVariable int id) {
        return departmentService.maxSalary(id);
    }

    @GetMapping("{id:\\d+}/salary/min")
    public Employee minSalary(@PathVariable int id) {
        return departmentService.minSalary(id);
    }

    @GetMapping("employees")
    public Map<Integer, List<Employee>> allEmployeesByDeps() {
        return departmentService.allEmployeesByDeps();
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String lastName,
                                @RequestParam String firstName,
                                @RequestParam int department,
                                @RequestParam int salary) {
        return employeeService.addEmployee(lastName, firstName, department, salary);
    }

    @GetMapping("/all")
    public Collection<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String lastName,
                                   @RequestParam String firstName,
                                   @RequestParam int department,
                                   @RequestParam int salary) {
        return employeeService.removeEmployee(lastName, firstName, department, salary);
    }
}
