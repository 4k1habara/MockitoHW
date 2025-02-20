package com.skypro.employees_mockito_hw.service;

import com.skypro.employees_mockito_hw.Employee;
import com.skypro.employees_mockito_hw.exceptions.EmployeeAlreadyAddedException;
import com.skypro.employees_mockito_hw.exceptions.EmployeeNotFoundException;
import com.skypro.employees_mockito_hw.exceptions.EmployeeStorageIsFullException;
import com.skypro.employees_mockito_hw.exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    private List<Employee> employees = new ArrayList<Employee>();
    private Employee employee1 = new Employee("Ivanov", "Ivan", 1, 10000);

    @Test
    void whenAddEmployee() {
        Employee expected = employee1;
        Employee actual = employeeService.addEmployee("Ivanov", "Ivan", 1, 10000);

        assertEquals(expected, actual);
    }

    @Test
    void whenAddSameEmployee() {
        Employee actual = employeeService.addEmployee("Ivanov", "Ivan", 1, 10000);

        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee("Ivanov", "Ivan", 1, 10000));
    }

    @Test
    void whenEmployeeStorageIsFull() {
        employeeService.addEmployee("Ivanov", "Ivan", 1, 10000);
        employeeService.addEmployee("Sergeev", "Sergey", 1, 20000);
        employeeService.addEmployee("Nikolaev", "Nikolay", 2, 30000);
        employeeService.addEmployee("Nikitin", "Nikita", 2, 20000);
        employeeService.addEmployee("A", "A", 2, 20000);
        employeeService.addEmployee("B", "B", 2, 20000);
        employeeService.addEmployee("C", "C", 2, 20000);
        employeeService.addEmployee("D", "D", 2, 20000);
        employeeService.addEmployee("E", "E", 2, 20000);
        employeeService.addEmployee("G", "G", 2, 20000);

        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEmployee("F", "F", 2, 20000));
    }

    @Test
    void whenInvalidInput() {

        assertThrows(InvalidInputException.class, () -> employeeService.addEmployee("1", "2", 1, 2));
    }

    @Test
    void whenRemoveEmployee() {

        employeeService.addEmployee("Ivanov", "Ivan", 1, 10000);

        Employee expected = employee1;
        Employee actual = new Employee("Ivanov", "Ivan", 1, 10000);

        assertEquals(expected, actual);
    }

    @Test
    void whenEmployeeNotFound() {

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee("Ivanov", "Ivan", 1, 10000));
    }

    @Test
    void findEmployee() {
        employeeService.addEmployee("Ivanov", "Ivan", 1, 10000);

        Employee expected = employee1;
        Employee actual = new Employee("Ivanov", "Ivan", 1, 10000);

        assertEquals(expected, actual);
    }

    @Test
    void getAllEmployees() {

        employees.add(employee1);
        employeeService.addEmployee("Ivanov", "Ivan", 1, 10000);

        Collection expected = Collections.unmodifiableList(employees);
        Collection actual = employeeService.getAllEmployees();

        assertEquals(expected, actual);
    }
}