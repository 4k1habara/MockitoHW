package com.skypro.employees_mockito_hw;

import com.skypro.employees_mockito_hw.service.EmployeeService;
import com.skypro.employees_mockito_hw.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        EmployeeService employeeService = new EmployeeServiceImpl();
//        employeeService.addEmployee("Ivanov", "Ivan", 1, 10000);
//        System.out.println(employeeService.getAllEmployees());
//        employeeService.removeEmployee("Ivanov", "Ivan", 1, 10000);

        List<Employee> employees = new ArrayList<Employee>();

        Employee employee = new Employee("Ivanov", "Ivan", 1, 10000);

        employees.add(employee);

        if (employees.contains(employee)) {
            employees.remove(employee);
        }
        System.out.println(employees);
    }
}
