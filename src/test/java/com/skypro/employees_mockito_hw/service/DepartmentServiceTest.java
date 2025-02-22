package com.skypro.employees_mockito_hw.service;

import com.skypro.employees_mockito_hw.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeServiceImpl employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl out;

    private Employee employee1 = new Employee("Ivanov", "Ivan", 1, 10000);
    private Employee employee2 = new Employee("Sergeev", "Sergey", 1, 20000);
    private Employee employee3 = new Employee("Nikolaev", "Nikolay", 2, 20000);
    private List<Employee> actualEmployees = new ArrayList<Employee>();
    private List<Employee> expectedEmployees = new ArrayList<Employee>();

    @BeforeEach
    public void setUp() {

        actualEmployees.add(employee1);
        actualEmployees.add(employee2);
        actualEmployees.add(employee3);
    }

    @AfterEach
    public void resetSettings() {
        Iterator<Employee> employeeIter = expectedEmployees.iterator();
        while (employeeIter.hasNext()) {
            Employee employee = employeeIter.next();
            if (!(employee == null)) {
                employeeIter.remove();
            }
        }
    }

    @Test
    void whenDepartmentNotPassed() {
        String expected = "Welcome To The Jungle!";
        String actual = out.welcome();

        assertEquals(expected, actual);
    }

    @Test
    void whenGetEmployeesByDep() {

        when(employeeServiceMock.getAllEmployees()).thenReturn(actualEmployees);

        expectedEmployees.add(employee1);
        expectedEmployees.add(employee2);

        List<Employee> expected = expectedEmployees;
        List<Employee> actual = out.getEmployeesByDep(1);

        assertEquals(expected, actual);
    }

    @Test
    void whenGetEmployeesByAnotherDep() {

        when(employeeServiceMock.getAllEmployees()).thenReturn(actualEmployees);

        expectedEmployees.add(employee3);

        List<Employee> expected = expectedEmployees;
        List<Employee> actual = out.getEmployeesByDep(2);

        assertEquals(expected, actual);
    }

    @Test
    void whenDepartmentNotExist() {

        when(employeeServiceMock.getAllEmployees()).thenReturn(actualEmployees);

        List<Employee> expected = expectedEmployees;
        List<Employee> actual = out.getEmployeesByDep(3);

        assertEquals(expected, actual);
    }

    @Test
    void whenSumSalary() {

        when(employeeServiceMock.getAllEmployees()).thenReturn(actualEmployees);

        int expected = 30000;
        int actual = out.sumSalary(1);

        assertEquals(expected, actual);
    }

    @Test
    void whenMaxSalary() {

        when(employeeServiceMock.getAllEmployees()).thenReturn(actualEmployees);

        Employee expected = employee2;
        Employee actual = out.maxSalary(1);

        assertEquals(expected, actual);
    }

    @Test
    void whenCollectionIsEmpty() {

        actualEmployees.remove(employee1);
        actualEmployees.remove(employee2);
        actualEmployees.remove(employee3);

        when(employeeServiceMock.getAllEmployees()).thenReturn(actualEmployees);

        Employee expected = null;
        Employee actual = out.maxSalary(1);

        assertEquals(expected, actual);
    }

    @Test
    void minSalary() {

        when(employeeServiceMock.getAllEmployees()).thenReturn(actualEmployees);

        Employee expected = employee1;
        Employee actual = out.minSalary(1);

        assertEquals(expected, actual);
    }

    @Test
    void allEmployeesByDeps() {

        expectedEmployees.add(employee1);
        expectedEmployees.add(employee2);
        expectedEmployees.add(employee3);

        when(employeeServiceMock.getAllEmployees()).thenReturn(actualEmployees);

        Map<Integer, List<Employee>> expected = expectedEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        Map<Integer, List<Employee>> actual = out.allEmployeesByDeps();

        assertEquals(expected, actual);
    }
}