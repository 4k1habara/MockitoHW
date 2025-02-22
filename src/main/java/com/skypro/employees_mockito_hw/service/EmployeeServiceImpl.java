package com.skypro.employees_mockito_hw.service;

import com.skypro.employees_mockito_hw.Employee;
import com.skypro.employees_mockito_hw.exceptions.EmployeeAlreadyAddedException;
import com.skypro.employees_mockito_hw.exceptions.EmployeeNotFoundException;
import com.skypro.employees_mockito_hw.exceptions.EmployeeStorageIsFullException;
import com.skypro.employees_mockito_hw.exceptions.InvalidInputException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final static int maxQuantity = 10;
    private final static List<Employee> employees = new ArrayList<>();

    @Override
    public Employee addEmployee(String lastName, String firstName, int department, int salary) {
        validateInput(firstName, lastName);

        Employee employee = new Employee(lastName, firstName, department, salary);
        if (employees.size() == EmployeeServiceImpl.maxQuantity) {
            throw new EmployeeStorageIsFullException();
        }
        if (!employees.contains(employee)) {
            StringUtils.containsOnly("A-Za-z ", String.valueOf(employee));
            employees.add(employee);
            return employee;
        }
        throw new EmployeeAlreadyAddedException();
    }

    @Override
    public Employee removeEmployee(String lastName, String firstName, int department, int salary) {
        validateInput(firstName, lastName);

        Employee employee = new Employee(lastName, firstName, department, salary);

        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String lastName, String firstName, int department, int salary) {
        validateInput(firstName, lastName);

        Employee employee = new Employee(lastName, firstName, department, salary);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableList(employees);
    }

    private void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }
}
